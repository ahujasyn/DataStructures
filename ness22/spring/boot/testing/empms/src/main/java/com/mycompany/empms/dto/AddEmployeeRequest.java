package com.mycompany.empms.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * represents request dto for adding employee
 */
public class AddEmployeeRequest {
    @NotBlank
    @Size(min=2,max=12)
    private String name;
    @Min(18) @Max(60)
    private int age;
    @Min(1)
    private long deptId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }
}
