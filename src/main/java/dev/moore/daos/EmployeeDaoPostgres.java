package dev.moore.daos;

import dev.moore.entities.Employee;
import dev.moore.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoPostgres implements EmployeeDAO{

    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into employee values (default,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());

            preparedStatement.execute();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            int generatedKey = keys.getInt("id");
            employee.setId(generatedKey);
            return employee;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet keys = ps.executeQuery();

            List<Employee> employeeList = new ArrayList();
            while(keys.next()){
                Employee employee = new Employee();
                employee.setId(keys.getInt("id"));
                employee.setFirstName(keys.getString("first_name"));
                employee.setLastName(keys.getString("last_name"));
                employeeList.add(employee);
            }
            return employeeList;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet keys = ps.executeQuery();
            keys.next();
            Employee employee = new Employee();
            employee.setId(keys.getInt("id"));
            employee.setFirstName(keys.getString("first_name"));
            employee.setLastName(keys.getString("last_name"));
            return employee;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update employee set first_name = ?, last_name = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,employee.getFirstName());
            ps.setString(2,employee.getLastName());
            ps.setInt(3,employee.getId());

            int result = ps.executeUpdate();
            return employee;
            //return result; maybe
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String expenseDeleteSql = "delete from expense where issuer_id = ?";
            PreparedStatement edPS = conn.prepareStatement(expenseDeleteSql);
            edPS.setInt(1,id);
            edPS.execute();

            String sql = "delete from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();


            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
