package com.example.testdemo;

public class Calculator {

    private Adder adder;

   public Calculator(Adder adder){
       this.adder=adder;
   }


   public int sum(int num1 , int num2){
       System.out.println("inside Calculator#sum(*)");
      int result= adder.add(num1, num2);
      return result;
   }

   public int increaseBy10(int num){
       System.out.println("inside Calculator#increaseBy10(*)");
       int result=sum(num,10);
       return result;
   }

}
