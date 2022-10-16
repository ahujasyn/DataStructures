package com.mycompany.empms.repository;

import com.mycompany.empms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * findByName(*) and findEmployeeByName(*) are equialent ,anyone can be used
 */
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByName(String name);

    @Query(" from Employee e  where name=:nameArg")
    List<Employee> findEmployeeByName(@Param("nameArg") String name);



}
