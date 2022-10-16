package com.mycompany.empms.service;

import com.mycompany.empms.dto.AddDepartmentRequest;
import com.mycompany.empms.dto.DepartmentDetails;
import com.mycompany.empms.entity.Department;
import com.mycompany.empms.exceptions.DepartmentNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface IDepartmentService {

    DepartmentDetails add(@NotNull @Valid AddDepartmentRequest requestData);

    Department findById(@Min(1)long deptId) throws DepartmentNotFoundException;

}
