package dev.moore.daos;

import dev.moore.entities.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoLocal implements EmployeeDAO{

    private final Map<Integer,Employee> employeeTable = new HashMap<>();
    private int idMaker = 1;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(idMaker++);
        employeeTable.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeTable.values());
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeTable.get(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee unUpdatedEmployee = employeeTable.get(employee.getId());
        if(unUpdatedEmployee == null){
            return null;
        }
        employeeTable.put(employee.getId(), employee);
        return employeeTable.get(employee.getId());
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        Employee employee = employeeTable.remove(id);
        return employee != null;
    }
}
