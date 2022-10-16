package com.example.demo.admin.service;

import com.example.demo.admin.dto.AdminDetails;
import com.example.demo.admin.dto.RegisterAdminRequest;
import com.example.demo.admin.entity.Admin;

public interface IAdminService  {
   AdminDetails register(RegisterAdminRequest request);
}
