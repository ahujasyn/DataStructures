package com.mycompany.empms.dao;

import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.entity.Department;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee>findByName(String name);

    List<Employee>findByNameAndAge(String name, int age);
    boolean existsByNameAndAge(String name, int age);

    @Query("from Employee where age >:ageParam ")
    List<Employee>findByAgeGreaterThan(@Param("ageParam") int age);


    List<Employee>findByDepartment(Department department);

    @Query("select emp from Employee emp join emp.department dept where dept.deptName=:dept")
    List<Employee>findByDepartmentName(@Param("dept")String deptName);


}
