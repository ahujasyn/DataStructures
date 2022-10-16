package com.nesstraining;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name="employees_data")
@Entity
public class Employee {
    @GeneratedValue
    @Id
    private  Long id;

    @Column(name="cname")
    private String name;


   @ManyToMany(mappedBy = "employees")
    private Set<Department> departments;

    private  int age;

    public Employee(){

    }

    public Employee(String name, int age){
        this.name=name;
        this.age=age;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> department) {
        this.departments = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee customer = (Employee) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
