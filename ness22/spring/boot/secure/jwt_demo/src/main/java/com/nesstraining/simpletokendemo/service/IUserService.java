package com.nesstraining.simpletokendemo.service;

import com.nesstraining.simpletokendemo.dto.LoginRequest;
import com.nesstraining.simpletokendemo.dto.RegisterRequest;
import com.nesstraining.simpletokendemo.dto.UserDetails;
import com.nesstraining.simpletokendemo.entity.User;
import com.nesstraining.simpletokendemo.exceptions.AppUserNotFoundException;
import com.nesstraining.simpletokendemo.exceptions.IncorrectCredentialsException;

import java.util.List;

public interface IUserService {


    UserDetails register(RegisterRequest request);


    String login(LoginRequest loginRequest)throws AppUserNotFoundException, IncorrectCredentialsException;

    UserDetails authenticateByToken(String token) throws AppUserNotFoundException;

    List<UserDetails> findAll();

}
