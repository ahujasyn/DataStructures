package com.example.demo.admin.repo;

import com.example.demo.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository  extends JpaRepository<Admin, Long> {
}
