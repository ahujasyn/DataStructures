package com.mycompany.empms.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.service.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerMvcTests {

    @MockBean
    IEmployeeService service;

    @Autowired
    MockMvc mvc;

    /**
     *  scenario: when employee exists
     *
     * @throws Exception
     */
    @Test
    public void testFetchById_1() throws Exception{
        long id=10;
        EmployeeDetails response=new EmployeeDetails();
        response.setId(id);
        response.setAge(25);
        response.setName("vinay");
        response.setDeptId(5);
        ObjectMapper mapper=new ObjectMapper();
        String expectedJson=mapper.writeValueAsString(response);
        System.out.println("expected json="+expectedJson);
        when(service.findEmployeeDetailsById(id)).thenReturn(response);
        String url="/employees/byid/"+id;
        mvc.perform(get(url)).
                andExpect(status().isOk()).
                andExpect(content().json(expectedJson));

    }

    /**
     *  scenario: when employee does not exist , EmployeeNotFoundException is thrown
     *
     * @throws Exception
     */
    @Test
    public void testFetchById_2() throws Exception{
        long id=290;
        String expectedBody="employee not found for id="+id;
        EmployeeNotFoundException exception=new EmployeeNotFoundException(expectedBody);
        when(service.findEmployeeDetailsById(id)).thenThrow(exception);
        /*
        useful api for stubbing in case you want some functionality to execute when a method is called
        when(service.findEmployeeDetailsById(id)).thenAnswer(new Answer(){
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                throw new EmployeeNotFoundException(expectedBody);
            }
        });*/
        String url="/employees/byid/"+id;
        mvc.perform(get(url)).
                andExpect(status().isNotFound()).
                andExpect(content().string(expectedBody));

    }



}
