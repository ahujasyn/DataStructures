package com.nesstraining.simpletokendemo.controller;

import com.nesstraining.simpletokendemo.dto.LoginRequest;
import com.nesstraining.simpletokendemo.dto.RegisterRequest;
import com.nesstraining.simpletokendemo.dto.UserDetails;
import com.nesstraining.simpletokendemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class AppController {

    @Autowired
    private IUserService service;

    @PostMapping
    public UserDetails register(@RequestBody RegisterRequest request){
      return   service.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) throws Exception{
        return service.login(loginRequest);
    }

    @GetMapping("/all")
    public List<UserDetails> findAll(){
        return service.findAll();
    }

}
