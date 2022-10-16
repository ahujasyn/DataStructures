package com.mycompany.empms.dto;

import javax.validation.constraints.Size;

public class AddDepartmentRequest {

    @Size(min=2,max=10)
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
