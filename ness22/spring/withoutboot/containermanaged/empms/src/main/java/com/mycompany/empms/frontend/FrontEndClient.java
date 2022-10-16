package com.mycompany.empms.frontend;

import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.exceptions.InvalidEmployeeAgeException;
import com.mycompany.empms.exceptions.InvalidEmployeeIdException;
import com.mycompany.empms.exceptions.InvalidEmployeeNameException;
import com.mycompany.empms.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

@Component
public class FrontEndClient {

    @Autowired
    private IEmployeeService service;


    public void render() {
        try {
            Employee emp1 = service.add("sadique", 27);
            display(emp1);
            Employee emp2 = service.add("ramanna", 28);
            display(emp2);
            long emp1Id = emp1.getId();
            System.out.println("fetching employee with id=" + emp1Id);
            Employee found = service.findById(emp1Id);
            display(found);

            System.out.println("changing ramanna's details in store");
            emp2.setAge(26);
            emp2 = service.update(emp2);
            display(emp2);
            System.out.println("fetching all employees and rendering");
            List<Employee> employees = service.findAll();
            displayEmployees(employees);

        } catch (InvalidEmployeeIdException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InvalidEmployeeNameException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InvalidEmployeeAgeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("something went wrong " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void display(Employee emp) {
        System.out.println("employee-" + emp.getId() + "-" + emp.getName() + "-" + emp.getAge());
    }

    public void displayEmployees(Collection<Employee> employees) {
        Consumer<Employee> consumer = this::display;
        employees.stream().forEach(consumer);
    }


}
