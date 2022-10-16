package com.mycompany.bidirectional;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(name ="employee_department")
@Entity
public class EmployeeDepartment {

    @GeneratedValue
    @Id
    private Long id;

    @JoinColumn(name="empid")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name="deptid")
    @ManyToOne
    private Department department;

    private LocalDate joinedDate;

    public EmployeeDepartment() {}

    public EmployeeDepartment(Employee employee, Department department){
     this.employee=employee;
     this.department=department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDepartment that = (EmployeeDepartment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
