package com.example.demo.user.controller;


import com.example.demo.JwtTokenUtil;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private IUserService userService;

    @GetMapping("/success")
    public String loginSuccess() {
        return "you are successfully logged in";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) throws Exception {
        String token = userService.login(request.getUsername(), request.getPassword());
        return token;
    }

}
