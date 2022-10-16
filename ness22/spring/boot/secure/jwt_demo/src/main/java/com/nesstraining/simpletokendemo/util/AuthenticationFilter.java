package com.nesstraining.simpletokendemo.util;


import com.nesstraining.simpletokendemo.dto.UserDetails;
import com.nesstraining.simpletokendemo.exceptions.AppUserNotFoundException;
import com.nesstraining.simpletokendemo.exceptions.IncorrectCredentialsException;
import com.nesstraining.simpletokendemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Autowired
    private IUserService service;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String token = httpRequest.getHeader("Authorization");
        System.out.println("token="+token);
        /*
        String uri = httpRequest.getRequestURI();

        if (!uri.startsWith("/users/all")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }*/
        try {
            if (token == null || token.isEmpty()) {
                throw new IncorrectCredentialsException("token can't be null or empty");
            }
            if(!token.startsWith("Bearer")){
                throw new IncorrectCredentialsException("invalid token");
            }
            token=token.substring(7);
            UserDetails details = service.authenticateByToken(token);
            httpRequest.setAttribute("username", details.getUsername());
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (AppUserNotFoundException | IncorrectCredentialsException e) {
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpResponse.getWriter().write(e.getMessage());
        }
    }
}
