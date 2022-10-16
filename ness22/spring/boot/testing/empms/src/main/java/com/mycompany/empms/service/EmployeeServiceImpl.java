package com.mycompany.empms.service;

import com.mycompany.empms.dao.IEmployeeRepository;
import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.dto.UpdateEmployeeRequest;
import com.mycompany.empms.entity.Department;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.*;
import com.mycompany.empms.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Transactional
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepo;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private EmployeeUtil employeeUtil;

    @Transactional
    @Override
    public EmployeeDetails add(AddEmployeeRequest requestData)
            throws DepartmentNotFoundException {
        Employee employee = newEmployee();
        employee.setName(requestData.getName());
        employee.setAge(requestData.getAge());
        Department department = departmentService.findById(requestData.getDeptId());
        employee.setDepartment(department);
        employee = employeeRepo.save(employee);
        EmployeeDetails desired = employeeUtil.toEmployeeDetails(employee);
        return desired;
    }

    Employee newEmployee() {
        return new Employee();
    }

    @Transactional
    @Override
    public Employee update(Employee employee)
            throws EmployeeNotFoundException {
        boolean exists = employeeRepo.existsById(employee.getId());
        if (!exists) {
            throw new EmployeeNotFoundException("employee not found for id=" + employee.getId());
        }
        employee = employeeRepo.save(employee);
        return employee;
    }

    @Transactional
    @Override
    public EmployeeDetails updateEmployeeDetails(UpdateEmployeeRequest requestData)
            throws EmployeeNotFoundException {
        Employee employee = employeeUtil.toEmployee(requestData);
        employee = update(employee);
        EmployeeDetails desired = employeeUtil.toEmployeeDetails(employee);
        return desired;
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findById(long id) throws EmployeeNotFoundException {
        Optional<Employee> optional = employeeRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EmployeeNotFoundException("employee doesn't exist for id=" + id);
    }


    @Transactional(readOnly = true)
    @Override
    public EmployeeDetails findEmployeeDetailsById(long id) throws EmployeeNotFoundException {
        Employee employee = findById(id);
        EmployeeDetails desired = employeeUtil.toEmployeeDetails(employee);
        return desired;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        PageRequest request = PageRequest.of(0, Integer.MAX_VALUE);
        Iterable<Employee> it = employeeRepo.findAll(request);
        List<Employee> list = new ArrayList<>();
        it.forEach((emp) -> list.add(emp));
        return list;
    }

    @Override
    public List<Employee> findAll(int pageNumber, int blockSize) {
        PageRequest request = PageRequest.of(pageNumber, blockSize);
        Iterable<Employee> it = employeeRepo.findAll(request);
        List<Employee> list = new ArrayList<>();
        it.forEach((emp) -> list.add(emp));
        return list;
    }

    @Override
    public List<EmployeeDetails> findAllEmployeesDetail(int pageNumber, int blockSize) {
        List<Employee> list = findAll(pageNumber, blockSize);
        return employeeUtil.toListEmployeesDetails(list);
    }

    @Override
    public List<EmployeeDetails> findAllEmployeeDetails() {
        List<Employee> employees = findAll();
        List<EmployeeDetails> desired = employeeUtil.toListEmployeesDetails(employees);
        return desired;
    }

    @Override
    public List<Employee> fetchByName(String name) {
        List<Employee> list = employeeRepo.findByName(name);
        return list;
    }

    @Override
    public List<Employee> fetchByNameAndAge(String name, int age) {
        List<Employee> list = employeeRepo.findByNameAndAge(name, age);
        return list;
    }

    @Override
    public List<Employee> fetchByAgeGreaterThan(int age) {
        List<Employee> list = employeeRepo.findByAgeGreaterThan(age);
        return list;
    }

    @Override
    public List<EmployeeDetails> findEmployeeDetailsByDepartmentId(long departmentId)
            throws DepartmentNotFoundException {
        Department department = departmentService.findById(departmentId);
        List<Employee> list = employeeRepo.findByDepartment(department);
        List<EmployeeDetails> desired = employeeUtil.toListEmployeesDetails(list);
        return desired;
    }

    @Override
    public List<EmployeeDetails> findEmployeeDetailsByDepartmentName(String departmentName) {
        List<Employee> list = employeeRepo.findByDepartmentName(departmentName);
        List<EmployeeDetails> desired = employeeUtil.toListEmployeesDetails(list);
        return desired;

    }


}
