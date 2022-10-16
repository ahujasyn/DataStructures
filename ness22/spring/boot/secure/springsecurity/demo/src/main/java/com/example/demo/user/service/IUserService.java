package com.example.demo.user.service;

import com.example.demo.user.exceptions.IncorrectCredentialsException;

public interface IUserService {
    String login(String username, String password) throws IncorrectCredentialsException;
}
