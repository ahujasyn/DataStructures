package com.example.cicddemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class Cicddemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cicddemo2Application.class, args);
	}
	
	
	@GetMapping("/ping")
	public String ping(){
	 return "ping back";
	}

}
