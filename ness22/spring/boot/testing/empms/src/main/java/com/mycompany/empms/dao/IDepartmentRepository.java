package com.mycompany.empms.dao;

import com.mycompany.empms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department,Long> {
}
