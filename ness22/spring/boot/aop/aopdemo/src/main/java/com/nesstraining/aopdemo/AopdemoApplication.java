package com.nesstraining.aopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(AopdemoApplication.class, args);
	    Calculator calcy=context.getBean(Calculator.class);
	    int result1=calcy.add(1,2);
		int result2=calcy.sub(10,6);
		calcy.validateEven(10);
	}

}
