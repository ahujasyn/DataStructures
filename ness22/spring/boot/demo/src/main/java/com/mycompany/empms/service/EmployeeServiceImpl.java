package com.mycompany.empms.service;

import com.mycompany.empms.dao.IEmployeeDao;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.exceptions.InvalidEmployeeAgeException;
import com.mycompany.empms.exceptions.InvalidEmployeeIdException;
import com.mycompany.empms.exceptions.InvalidEmployeeNameException;
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


    // spring aop
    @Transactional
    @Override
    public Employee add(String name, int age) throws InvalidEmployeeNameException, InvalidEmployeeAgeException {
        validateName(name);
        validateAge(age);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee=dao.add(employee);
        return employee;
    }

    @Transactional
    @Override
    public Employee update(Employee employee)
            throws InvalidEmployeeNameException, InvalidEmployeeAgeException, EmployeeNotFoundException, InvalidEmployeeIdException {
        validateName(employee.getName());
        validateAge(employee.getAge());
        validateId(employee.getId());
        boolean exists = dao.existById(employee.getId());
        if (!exists) {
            throw new EmployeeNotFoundException("employee not found for id=" + employee.getId());
        }
        dao.update(employee);
        return employee;
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findById(long id) throws InvalidEmployeeIdException, EmployeeNotFoundException {
        validateId(id);
        Optional<Employee> optional = dao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EmployeeNotFoundException("employee doesn't exist for id=" + id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        return dao.findAll();
    }



    void validateId(long id) throws InvalidEmployeeIdException {
        if (id <= 0) {
            throw new InvalidEmployeeIdException("id can't be negative and zero");
        }
    }

    void validateName(String name) throws InvalidEmployeeNameException {
        if (name == null || name.isEmpty()) {
            throw new InvalidEmployeeNameException("name can't be null or empty");
        }
    }

    void validateAge(int age) throws InvalidEmployeeAgeException {
        if (age < 18 || age > 60) {
            throw new InvalidEmployeeAgeException("age can't be be smaller than 18 or greater than 60");
        }
    }

}
