package com.mycompany.empms.service;

import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.dto.UpdateEmployeeRequest;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.exceptions.InvalidEmployeeAgeException;
import com.mycompany.empms.exceptions.InvalidEmployeeIdException;
import com.mycompany.empms.exceptions.InvalidEmployeeNameException;
import com.mycompany.empms.repository.IEmployeeRepository;
import com.mycompany.empms.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Transactional
@Service// of the type @Component
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository repo;

    @Autowired
    private EmployeeUtil util;


    // spring aop
    @Transactional
    @Override
    public EmployeeDetails add(AddEmployeeRequest request) throws InvalidEmployeeNameException, InvalidEmployeeAgeException {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee= repo.save(employee);
        EmployeeDetails desired=util.toDetails(employee);
        return desired;
    }

    @Transactional
    @Override
    public EmployeeDetails update(UpdateEmployeeRequest requestsData)
            throws InvalidEmployeeNameException, InvalidEmployeeAgeException, EmployeeNotFoundException, InvalidEmployeeIdException {
        boolean exists = repo.existsById(requestsData.getId());
        if (!exists) {
            throw new EmployeeNotFoundException("employee not found for id=" + requestsData.getId());
        }
        Employee employee=findById(requestsData.getId());
        employee.setAge(requestsData.getAge());
        employee.setName(requestsData.getName());
        repo.save(employee);
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
        Optional<Employee> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EmployeeNotFoundException("employee doesn't exist for id=" + id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeDetails> findAll() {
       List<Employee>employees= repo.findAll();
       return util.toDetailsList(employees);
    }


    @Transactional(readOnly = true)
    public List<EmployeeDetails>findAll(int pageNumber){
        int blockSize=2;
        PageRequest request=PageRequest.of(pageNumber,blockSize);
        List<Employee>employees=new ArrayList<>();
        Iterable<Employee>fetched= repo.findAll(request);
        fetched.forEach(emp->employees.add(emp));
        return util.toDetailsList(employees);
    }

/*
   firstindex, blocksize
   Query qeuery=em.createQuery("")
   query.setFirstIndex(fistindex)
   query.setBlockSize(block)
 */
    @Override
    public List<EmployeeDetails> findEmployeeDetailsByName(String name) {
       List<Employee>list =repo.findEmployeeByName(name);
        return util.toDetailsList(list);
    }

}
