package dev.moore.daotests;


import dev.moore.entities.Employee;
import dev.moore.daos.EmployeeDAO;
import dev.moore.daos.EmployeeDaoPostgres;
import dev.moore.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table employee(\n" +
                    "\n" +
                    "\tid serial primary key,\n" +
                    "\tfirst_name varchar(100) not null,\n" +
                    "\tlast_name varchar(100) not null\n" +
                    "\t\n" +
                    ");";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee = new Employee("Bob","Bobson");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }

    @Test
    @Order(2)
    void get_all_employees_test(){
        List<Employee> employeeList = employeeDAO.getAllEmployees();
        Assertions.assertEquals(1,employeeList.size());
        Assertions.assertEquals("Bob",employeeList.get(0).getFirstName());
    }

    @Test
    @Order(3)
    void get_employee_by_id_test(){
        Employee employee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Bob",employee.getFirstName());
    }

    @Test
    @Order(4)
    void update_employee_test(){
        Employee employee = new Employee("Billy","Bobson");
        employee.setId(1);
        employeeDAO.updateEmployee(employee);
        Assertions.assertEquals("Billy",employeeDAO.getEmployeeById(1).getFirstName());
    }

    @Test
    @Order(5)
    void delete_employee_by_id_test(){
        boolean result = employeeDAO.deleteEmployeeById(1);
        Assertions.assertTrue(result);
    }
}
