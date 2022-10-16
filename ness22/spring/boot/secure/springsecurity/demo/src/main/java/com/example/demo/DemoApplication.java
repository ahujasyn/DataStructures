package com.example.demo;

import com.example.demo.admin.dto.RegisterAdminRequest;
import com.example.demo.admin.service.IAdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(DemoApplication.class, args);
	    IAdminService adminService=context.getBean(IAdminService.class);
		RegisterAdminRequest request=new RegisterAdminRequest();
		request.setUsername("tushar");
		request.setPassword("1234");
		request.setCompany("ness");
	    adminService.register(request);

	 }

}
