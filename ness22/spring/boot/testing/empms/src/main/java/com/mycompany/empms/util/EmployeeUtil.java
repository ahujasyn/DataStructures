package com.mycompany.empms.util;

import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.dto.UpdateEmployeeRequest;
import com.mycompany.empms.entity.Department;
import com.mycompany.empms.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EmployeeUtil {

    /*
    old
   public EmployeeDetails toEmployeeDetails(Employee employee){
       EmployeeDetails details = new EmployeeDetails();
       details.setId(employee.getId());
       details.setName(employee.getName());
       details.setAge(employee.getAge());
       return details;
   }
   */

    /**
     * utility class BeanUtils used to copy poperties from employee to EmolyeeDetails
     * @param employee
     * @return employeedetais
     */
    public EmployeeDetails toEmployeeDetails(Employee employee){
        EmployeeDetails details = new EmployeeDetails();
        BeanUtils.copyProperties(employee,details);
        Department department=employee.getDepartment();
        details.setDeptId(department.getId());
        return details;
    }

    public Employee toEmployee(UpdateEmployeeRequest request){
        Employee desired=new Employee();
        BeanUtils.copyProperties(request,desired);
        return desired;
    }

   public List<EmployeeDetails> toListEmployeesDetails(Collection<Employee> employees){
       Function<Employee,EmployeeDetails>function=this::toEmployeeDetails;
      List<EmployeeDetails>desired= employees.stream().map(function).collect(Collectors.toList());
      return desired;
   }

}
