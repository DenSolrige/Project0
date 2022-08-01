package dev.moore.daotests;


import dev.moore.api.Employee;
import dev.moore.daos.EmployeeDAO;
import dev.moore.daos.EmployeeDaoLocal;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoLocal();

    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee = new Employee("Bob");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }

    @Test
    @Order(2)
    void get_all_employees_test(){
        List<Employee> employeeList = employeeDAO.getAllEmployees();
        Assertions.assertEquals(1,employeeList.size());
        Assertions.assertEquals("Bob",employeeList.get(0).getName());
    }

    @Test
    @Order(3)
    void get_employee_by_id_test(){
        Employee employee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Bob",employee.getName());
    }

    @Test
    @Order(4)
    void update_employee_test(){
        Employee employee = new Employee("Billy");
        employeeDAO.updateEmployee(1, employee);
        Assertions.assertEquals("Billy",employeeDAO.getEmployeeById(1).getName());
    }

    @Test
    @Order(5)
    void delete_employee_by_id_test(){
        boolean result = employeeDAO.deleteEmployeeById(1);
        Assertions.assertTrue(result);
    }
}
