package com.example.demo.admin.service;

import com.example.demo.admin.dto.AdminDetails;
import com.example.demo.admin.dto.RegisterAdminRequest;
import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.repo.IAdminRepository;
import com.example.demo.admin.util.AdminUtil;
import com.example.demo.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IAdminRepository adminRepo;

    @Autowired
    private AdminUtil adminUtil;


    @Override
    public AdminDetails register(RegisterAdminRequest request) {
        Set<String> roles=new HashSet<>();
        roles.add("ADMIN");
        User user=new User(request.getUsername(),request.getPassword(),roles);
        Admin admin=new Admin();
        admin.setUser(user);
        admin.setCompany(request.getCompany());
        admin=adminRepo.save(admin);
        return adminUtil.toDetails(admin);
    }
}
