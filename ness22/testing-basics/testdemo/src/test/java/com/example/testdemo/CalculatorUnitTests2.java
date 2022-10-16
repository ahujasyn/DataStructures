package com.example.testdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculatorUnitTests2 {

    @Mock
    Adder adder;


    @InjectMocks
    @Spy
    Calculator calculator;


    @BeforeEach
    public void setup() {
    }

    @Test
    public void testSum_1() {
        System.out.println("inside tescase testSum_1");
        when(adder.add(1, 2)).thenReturn(3);
        int result = calculator.sum(1, 2);
        assertEquals(3, result);
        verify(adder).add(1, 2);
    }

    /**
     * input : 5
     */
    @Test
    public void testIncreaseBy10_1(){
        System.out.println("inside testcase testIncreaseBy10_1");
       // when(calculator.sum(5,10)).thenReturn(15);
        doReturn(15).when(calculator).sum(5,10);
        int result=calculator.increaseBy10(5);
        assertEquals(15,result);
    }
}
