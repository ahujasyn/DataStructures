package com.mycompany.empms.service;

import com.mycompany.empms.dao.IEmployeeRepository;
import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.entity.Department;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.util.EmployeeUtil;
import org.hibernate.jdbc.Expectation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplUnitTest {

    @Mock
    IEmployeeRepository employeeRepo;
    @Mock
    EmployeeUtil employeeUtil;
    @Mock
    IDepartmentService departmentService;

    @InjectMocks
    @Spy
    EmployeeServiceImpl employeeService;

    /**
     * scenario: employee successfully fetched
     * input : id =1
     * expectation : expected employee will be returned
     */
    @Test
    public void testFindById_1() throws Exception {
        long id = 1;
        Employee expected = mock(Employee.class);
        Optional<Employee> optional = Optional.of(expected);
        when(employeeRepo.findById(id)).thenReturn(optional);
        Employee result = employeeService.findById(id);
        assertSame(expected, result);
        verify(employeeRepo).findById(id);
    }

    /**
     * scenario: employee not found
     *
     * @throws Exception
     */
    @Test
    public void testFindById_2() throws Exception {
        long id = 2;
        Optional<Employee> optional = Optional.empty();
        when(employeeRepo.findById(id)).thenReturn(optional);
        Executable execute = () -> employeeService.findById(id);
        assertThrows(NullPointerException.class, execute);
        verify(employeeRepo).findById(id);
    }


    @Test
    public void testAdd_1() throws Exception {
        AddEmployeeRequest request = new AddEmployeeRequest();
        long deptId = 10;
        request.setName("sachin");
        request.setAge(49);
        request.setDeptId(deptId);
        Employee employee = mock(Employee.class);
        doReturn(employee).when(employeeService).newEmployee();
        Department department = mock(Department.class);
        when(departmentService.findById(deptId)).thenReturn(department);
        when(employeeRepo.save(employee)).thenReturn(employee);
        EmployeeDetails details = mock(EmployeeDetails.class);
        when(employeeUtil.toEmployeeDetails(employee)).thenReturn(details);
        EmployeeDetails result = employeeService.add(request);
        assertSame(details, result);
        verify(employee).setDepartment(department);
        verify(employee).setName(request.getName());
        verify(employee).setAge(request.getAge());

    }


}