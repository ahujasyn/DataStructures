package com.mycompany.empms.service;


import com.mycompany.empms.entity.Department;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.util.EmployeeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Import({EmployeeUtil.class, DepartmentServiceImpl.class, EmployeeServiceImpl.class})
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceImplJpaTests {

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
    @Test
    public void testFindById_1() throws Exception {
        String deptName="dev";
        String empName="rajiv";
        int age=28;
        Department department = new Department();
        department.setDeptName(deptName);
        em.persist(department);
        Employee employee = new Employee();
        employee.setName(empName);
        employee.setDepartment(department);
        employee.setAge(age);
        em.persist(employee);
        long empId = employee.getId();
        Employee result = employeeService.findById(empId);
        assertEquals(employee,result);
        assertEquals(department,result.getDepartment());
        assertEquals(empName,result.getName());
        assertEquals(age,result.getAge());
    }

    /**
     * scenario: Employee doesn't exist in store
     */
    @Test
    public void testFindById_2() throws Exception{
      Executable execute=()->employeeService.findById(12);
      assertThrows(EmployeeNotFoundException.class,execute );
    }

}
