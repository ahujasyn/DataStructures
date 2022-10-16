package com.mycompany.empms.service;


import com.mycompany.empms.entity.Department;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.util.EmployeeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
public class EmployeeServiceImplSpringBootTests {

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    DepartmentServiceImpl departmentService;

    @Autowired
    EmployeeUtil employeeUtil;

    @Autowired
    EntityManager em;

    /**
     * scenario: employee succcessfully fetched
     * precondition: employee exist in store, department exist
     */
    @Transactional
    @Test
    public void testFindById_1() throws Exception {
        String deptName="dev";
        String empName="rajiv";
        Department department = new Department();
        department.setDeptName(deptName);
        em.persist(department);
        Employee employee = new Employee();
        employee.setName(empName);
        employee.setDepartment(department);
        employee.setAge(28);
        em.persist(employee);
        long empId = employee.getId();
        Employee result = employeeService.findById(empId);
        assertEquals(employee,result);
        assertEquals(department,result.getDepartment());
        assertEquals(empName,result.getName());
    }

    /**
     * scenario: Employee doesn't exist in store
     */
    @Transactional
    @Test
    public void testFindById_2() throws Exception{
      Executable execute=()->employeeService.findById(12);
      assertThrows(EmployeeNotFoundException.class,execute );
    }


    /**
     * scenario: Invalid id (id is negative)
     */
    @Transactional
    @Test
    public void testFindById_3() throws Exception{
        long id=-20;
        Executable execute=()->employeeService.findById(id);
        assertThrows(ConstraintViolationException.class,execute );
    }
}
