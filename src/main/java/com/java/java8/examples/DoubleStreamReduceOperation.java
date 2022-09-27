package com.java.java8.examples;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 * So here will  use reduce function of java 8 with double.
 */
public class DoubleStreamReduceOperation {

    public static void main(String[] args) {
        DoubleStream doubleStream = DoubleStream.of(1.1,2.1,4,5,6,7);

     //   OptionalDouble optionalDouble = doubleStream.reduce(Double::sum);
        OptionalDouble optionalDouble1 = doubleStream.reduce((a,b)->a/2);

       // System.out.println("double "+optionalDouble.getAsDouble());

        System.out.println("optional Double "+optionalDouble1.getAsDouble());

    }
}
