package dev.moore.services;

import dev.moore.entities.Employee;
import dev.moore.daos.EmployeeDAO;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        return this.employeeDAO.createEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return this.employeeDAO.deleteEmployeeById(id);
    }
}
