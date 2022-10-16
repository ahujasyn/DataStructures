package com.mycompany.empms.controllers;

import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.service.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRestControllerUnitTest {

    @Mock
    IEmployeeService employeeService;

    @InjectMocks
    @Spy
    EmployeeRestController controller;

    /**
     * scenario: employee fetched from service
     * @throws Exception
     */
    @Test
    public void testFetchById_1()throws Exception {
        long id=10;
        EmployeeDetails response=mock(EmployeeDetails.class);
        when(employeeService.findEmployeeDetailsById(id)).thenReturn(response);
        EmployeeDetails found=controller.fetchById(id);
        assertSame(response, found);
        verify(employeeService).findEmployeeDetailsById(id);
    }


    /**
     * scenario: employee not fetched from service and EmployeeNotFoundException is thrown
     * @throws Exception
     * verify: EmployeeNot
     */
    @Test
    public void testFetchById_2()throws Exception {
        int id=20;
        when(employeeService.findEmployeeDetailsById(id)).thenThrow(EmployeeNotFoundException.class);
        Executable executable=()->employeeService.findEmployeeDetailsById(id);
        assertThrows(EmployeeNotFoundException.class,executable);
        verify(employeeService).findEmployeeDetailsById(id);

    }


}