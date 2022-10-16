package com.example.testdemo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CalculatorUnitTests1 {

    Calculator calculator;

    Adder adder;

    @BeforeEach
    public void setup() {
        adder = mock(Adder.class);
        Calculator calcy = new Calculator(adder);
        calculator= spy(calcy);
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
