package com.nesstraining.simpletokendemo;

import com.nesstraining.simpletokendemo.util.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleTokendemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleTokendemoApplication.class, args);
    }

    @Bean
    public AuthenticationFilter authenticationFilter(){
        return new AuthenticationFilter();
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter>register(){
        FilterRegistrationBean<AuthenticationFilter>registrationBean=new FilterRegistrationBean<>();
        registrationBean.setFilter(authenticationFilter());
        registrationBean.addUrlPatterns("/users/all");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
