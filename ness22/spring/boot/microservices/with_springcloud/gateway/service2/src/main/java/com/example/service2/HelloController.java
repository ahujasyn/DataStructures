package com.example.service2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RestController
public class HelloController {

    @GetMapping("/get")
    public String getProduct(){
        System.out.println("***inside get product");
        return "samsung";
    }

}
