package com.nesstraining.aopdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public int add(int num1, int num2){
        System.out.println("****inside add");
        int result= num1+num2;
        return result;
    }

    public int sub(int num1, int num2){
        System.out.println("***inside sub");
        return num1-num2;
    }

    public void validateEven(int num){
        System.out.println("inside validateEven");
        if(num%2!=0){
            throw new RuntimeException("number is not even");
        }
    }

}
