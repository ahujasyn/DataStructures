package com.mycompany.empms.util;

import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeUtil {


    public EmployeeDetails toDetails(Employee employee){
        EmployeeDetails desired=new EmployeeDetails();
        desired.setId(employee.getId());
        desired.setName(employee.getName());
        desired.setAge(employee.getAge());
        return desired;
    }

    public List<EmployeeDetails> toDetailsList(Collection<Employee>employees){
      List<EmployeeDetails>desired=  employees.stream().
                map(emp->toDetails(emp))
                .collect(Collectors.toList());
       return desired;
    }

}
