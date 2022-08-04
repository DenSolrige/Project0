package dev.moore.services;

import dev.moore.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployeeById(int id);

}
