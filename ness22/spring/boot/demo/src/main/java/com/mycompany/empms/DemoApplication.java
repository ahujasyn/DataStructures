package com.mycompany.empms;

import com.mycompany.empms.frontend.FrontEndClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Configation
 * @ComponentScan
 * @AutoConfigure
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(DemoApplication.class, args);
		FrontEndClient frontend=context.getBean(FrontEndClient.class);
	    frontend.render();
	}



}
