package com.mycompany.empms.dao;

import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDao {

    void add(Employee employee);

    Employee update(Employee employee);

    Optional<Employee> findById(long id);

    boolean existById(long id);

    List<Employee> findAll();

}
