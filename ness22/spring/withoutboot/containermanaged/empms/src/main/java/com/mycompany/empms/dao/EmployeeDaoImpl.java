package com.mycompany.empms.dao;

import com.mycompany.empms.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository // of the type @Component
public class EmployeeDaoImpl implements IEmployeeDao {

    @PersistenceContext
    // @Autowired
    private EntityManager entityManager;

    @Override
    public void add(Employee employee) {
        entityManager.merge(employee);
    }


    @Override
    public boolean existById(long id) {
        Optional<Employee> optional = findById(id);
        return optional.isPresent();
    }

    @Override
    public Employee update(Employee employee) {
        employee = entityManager.merge(employee);
        return employee;
    }

    @Override
    public Optional<Employee> findById(long id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee == null) {
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    @Override
    public List<Employee> findAll() {
        String queryStr = "from Employee";
        TypedQuery<Employee> query = entityManager.createQuery(queryStr, Employee.class);
        List<Employee> list = query.getResultList();
        return list;
    }
}
