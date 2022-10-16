package com.nesstraining;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Table(name="dept")
@Entity
public class Department {

    @GeneratedValue
    @Id
    private  Integer id;

    private  String name;

    @JoinTable(name = "dept_emp",
            joinColumns = @JoinColumn(name = "dept_id"), inverseJoinColumns = @JoinColumn(name = "emp_id"))
    @ManyToMany
    private Set<Employee>employees;

    public Set<Employee>getEmployees(){
        return employees;
    }
    public void setEmployees(Set<Employee>emps){
        this.employees=emps;
    }

    public Department(){

    }

    public Department(String name){
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
