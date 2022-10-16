package com.example.demo.admin.util;

import com.example.demo.admin.dto.AdminDetails;
import com.example.demo.admin.entity.Admin;
import com.example.demo.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AdminUtil {
    public AdminDetails toDetails(Admin admin){
        User user=admin.getUser();
        AdminDetails desired=new AdminDetails();
        desired.setUsername(user.getUsername());
        desired.setPassword(user.getPassword());
        desired.setCompany(admin.getCompany());
        return desired;
    }
}
