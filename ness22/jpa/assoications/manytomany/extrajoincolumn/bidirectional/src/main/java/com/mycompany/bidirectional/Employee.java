package com.mycompany.bidirectional;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name="employees_data")
@Entity
public class Employee {
    @GeneratedValue
    @Id
    private Long id;
    @Column(name="empname",nullable=false)
    private String name;

    @Column(nullable=false)
    private int age;

    public Employee() {}

    public Employee(String name, int age){
        setName(name);
        setAge(age);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
