package com.mycompany.bidirectional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class Demo {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("empms");
    private EntityManager em = emf.createEntityManager();


    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.start();
    }

    public void start() {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Department department1 = new Department();
        department1.setDeptName("dev");
        em.persist(department1);

        Department department2 = new Department();
        department2.setDeptName("testing");
        em.persist(department2);


        Employee employee1 = new Employee();
        employee1.setName("rajiv");
        employee1.setAge(28);
        em.persist(employee1);

        Employee employee2 = new Employee();
        employee2.setName("ramanna");
        employee2.setAge(28);
        em.persist(employee2);

        transaction.commit();

        LocalDate joinedDate1 = LocalDate.of(2022, 1, 10);
        addEmployeeToDepartment(department1, employee1, joinedDate1);


        LocalDate emp2Dept1JoinedDate = LocalDate.of(2022, 1, 20);
        addEmployeeToDepartment(department1, employee2, emp2Dept1JoinedDate);


        LocalDate emp2Dept2JoinedDate = LocalDate.of(2022, 1, 27);
        addEmployeeToDepartment(department2, employee2, emp2Dept2JoinedDate);


        System.out.println("****employees inserted");
        displayEmployee(employee1);
        displayEmployee(employee2);

        System.out.println("*****department1 details");
        displayDepartment(department1);

        System.out.println("*****department2 details");
        displayDepartment(department2);

        em.close();
        emf.close();
    }

    EmployeeDepartment addEmployeeToDepartment(Department department, Employee employee, LocalDate joinedDate) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        EmployeeDepartment employeeDepartment = new EmployeeDepartment(employee, department);
        employeeDepartment.setJoinedDate(joinedDate);
        em.persist(employeeDepartment);
        transaction.commit();
        return employeeDepartment;
    }


    public List<EmployeeDepartment> findEmployeesByDepartment(Department department) {
        String queryStr = "from EmployeeDepartment where department=:dept";
        TypedQuery<EmployeeDepartment> query = em.createQuery(queryStr, EmployeeDepartment.class);
        query.setParameter("dept", department);
        List<EmployeeDepartment> list = query.getResultList();
        return list;
    }


    public void displayEmployee(Employee emp) {
        System.out.println("emp-" + emp.getId() + "-" + emp.getName() + "-" + emp.getAge());

    }

    public void displayDepartment(Department department) {
        System.out.println("department-" + department.getId() + " " + department.getDeptName());
        List<EmployeeDepartment> employees = findEmployeesByDepartment(department);
        employees.stream().forEach(empdept -> {
            System.out.print("joined Date=" + empdept.getJoinedDate());
            displayEmployee(empdept.getEmployee());
        });
    }


    void displayCollection(Collection<Employee> collection) {
        Consumer<Employee> consumer = this::displayEmployee;
        collection.stream().forEach(consumer);
    }

}
