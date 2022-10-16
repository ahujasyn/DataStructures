package com.mycompany.empms.service;

import com.mycompany.empms.dao.IEmployeeDao;
import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.dto.UpdateEmployeeRequest;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.exceptions.InvalidEmployeeAgeException;
import com.mycompany.empms.exceptions.InvalidEmployeeIdException;
import com.mycompany.empms.exceptions.InvalidEmployeeNameException;
import com.mycompany.empms.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Transactional
@Service// of the type @Component
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeDao dao;

    @Autowired
    private EmployeeUtil util;


    // spring aop
    @Transactional
    @Override
    public EmployeeDetails add(AddEmployeeRequest request) throws InvalidEmployeeNameException, InvalidEmployeeAgeException {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee=dao.add(employee);
        EmployeeDetails desired=util.toDetails(employee);
        return desired;
    }

    @Transactional
    @Override
    public EmployeeDetails update(UpdateEmployeeRequest requetsData)
            throws InvalidEmployeeNameException, InvalidEmployeeAgeException, EmployeeNotFoundException, InvalidEmployeeIdException {
        boolean exists = dao.existById(requetsData.getId());
        if (!exists) {
            throw new EmployeeNotFoundException("employee not found for id=" + requetsData.getId());
        }
        Employee employee=findById(requetsData.getId());
        employee.setAge(requetsData.getAge());
        employee.setName(requetsData.getName());
        dao.update(employee);
        EmployeeDetails desired=util.toDetails(employee);
        return desired;
    }


    @Transactional(readOnly = true)
    @Override
    public EmployeeDetails findDetailsById(long id) throws InvalidEmployeeIdException, EmployeeNotFoundException {
       Employee employee=findById(id);
        EmployeeDetails desired=util.toDetails(employee);
        return desired;
    }

    @Transactional(readOnly = true)
    public Employee findById(long id) throws InvalidEmployeeIdException, EmployeeNotFoundException {
        Optional<Employee> optional = dao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EmployeeNotFoundException("employee doesn't exist for id=" + id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeDetails> findAll() {
       List<Employee>employees= dao.findAll();
       return util.toDetailsList(employees);
    }



}
