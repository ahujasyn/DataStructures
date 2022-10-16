package com.mycompany.empms.service;

import com.mycompany.empms.dao.IDepartmentRepository;
import com.mycompany.empms.dto.AddDepartmentRequest;
import com.mycompany.empms.dto.DepartmentDetails;
import com.mycompany.empms.entity.Department;
import com.mycompany.empms.exceptions.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

    @Autowired
    private IDepartmentRepository repo;

    @Override
    public DepartmentDetails add(AddDepartmentRequest requestData) {
        Department department=new Department();
        department.setDeptName(requestData.getDeptName());
        department=repo.save(department);
        DepartmentDetails desired=new DepartmentDetails();
        desired.setId(department.getId());
        desired.setDeptName(department.getDeptName());
        return desired;
    }

    @Override
    public Department findById(long deptId) throws DepartmentNotFoundException{
        Optional<Department>optional= repo.findById(deptId);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new DepartmentNotFoundException("department not found for id="+deptId);
    }
}
