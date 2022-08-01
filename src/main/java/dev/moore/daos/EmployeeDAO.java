package dev.moore.daos;

import dev.moore.api.Employee;

import java.util.List;

public interface EmployeeDAO {

    //Create
    Employee createEmployee(Employee employee);

    //Read
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);

    //Update
    Employee updateEmployee(int id, Employee employee);

    //Delete
    boolean deleteEmployeeById(int id);
}
