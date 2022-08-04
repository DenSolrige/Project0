package dev.moore.daos;

import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;
import dev.moore.entities.ExpenseType;
import dev.moore.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoPostgres implements ExpenseDAO{

    @Override
    public Expense createExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into expense values (default,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,expense.getExpenseStatus().toString());
            preparedStatement.setString(2,expense.getDescription());
            preparedStatement.setString(3,expense.getExpenseType().toString());
            preparedStatement.setInt(4,expense.getIssuerId());
            preparedStatement.setInt(5,expense.getExpenseValue());

            preparedStatement.execute();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            int generatedKey = keys.getInt("id");
            expense.setId(generatedKey);
            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet keys = preparedStatement.executeQuery();

            List<Expense> expenseList = new ArrayList();
            while(keys.next()){
                Expense expense = new Expense();
                expense.setId(keys.getInt("id"));
                expense.setExpenseStatus(ExpenseStatus.valueOf(keys.getString("expense_status")));
                expense.setDescription(keys.getString("description"));
                expense.setExpenseType(ExpenseType.valueOf(keys.getString("expense_type")));
                expense.setIssuerId(keys.getInt("issuer_id"));
                expense.setExpenseValue(keys.getInt("expense_value"));
                expenseList.add(expense);
            }
            return expenseList;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getExpensesByStatus(ExpenseStatus status) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense where expense_status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,status.toString());

            ResultSet keys = ps.executeQuery();

            List<Expense> expenseList = new ArrayList();
            while(keys.next()) {
                Expense expense = new Expense();
                expense.setId(keys.getInt("id"));
                expense.setExpenseStatus(ExpenseStatus.valueOf(keys.getString("expense_status")));
                expense.setDescription(keys.getString("description"));
                expense.setExpenseType(ExpenseType.valueOf(keys.getString("expense_type")));
                expense.setIssuerId(keys.getInt("issuer_id"));
                expense.setExpenseValue(keys.getInt("expense_value"));
                expenseList.add(expense);
            }
            return expenseList;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getExpensesByIssuerId(int issuerId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense where issuer_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,issuerId);

            ResultSet keys = ps.executeQuery();

            List<Expense> expenseList = new ArrayList();
            while(keys.next()) {
                Expense expense = new Expense();
                expense.setId(keys.getInt("id"));
                expense.setExpenseStatus(ExpenseStatus.valueOf(keys.getString("expense_status")));
                expense.setDescription(keys.getString("description"));
                expense.setExpenseType(ExpenseType.valueOf(keys.getString("expense_type")));
                expense.setIssuerId(keys.getInt("issuer_id"));
                expense.setExpenseValue(keys.getInt("expense_value"));
                expenseList.add(expense);
            }
            return expenseList;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet keys = ps.executeQuery();
            keys.next();
            Expense expense = new Expense();
            expense.setId(keys.getInt("id"));
            expense.setExpenseStatus(ExpenseStatus.valueOf(keys.getString("expense_status")));
            expense.setDescription(keys.getString("description"));
            expense.setExpenseType(ExpenseType.valueOf(keys.getString("expense_type")));
            expense.setIssuerId(keys.getInt("issuer_id"));
            expense.setExpenseValue(keys.getInt("expense_value"));

            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set expense_status = ?, description = ?, expense_type = ?, issuer_id = ?, expense_value = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,expense.getExpenseStatus().toString());
            ps.setString(2,expense.getDescription());
            ps.setString(3,expense.getExpenseType().toString());
            ps.setInt(4,expense.getIssuerId());
            ps.setInt(5,expense.getExpenseValue());
            ps.setInt(6,expense.getId());

            int result = ps.executeUpdate();
            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense approveExpenseStatus(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set expense_status = 'Approved' where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ps.execute();

            String sql2 = "select * from expense where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1,id);

            ResultSet keys = ps2.executeQuery();
            keys.next();
            Expense expense = new Expense();
            expense.setId(keys.getInt("id"));
            expense.setExpenseStatus(ExpenseStatus.valueOf(keys.getString("expense_status")));
            expense.setDescription(keys.getString("description"));
            expense.setExpenseType(ExpenseType.valueOf(keys.getString("expense_type")));
            expense.setIssuerId(keys.getInt("issuer_id"));
            expense.setExpenseValue(keys.getInt("expense_value"));

            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense denyExpenseStatus(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set expense_status = 'Denied' where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ps.execute();

            String sql2 = "select * from expense where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1,id);

            ResultSet keys = ps2.executeQuery();
            keys.next();
            Expense expense = new Expense();
            expense.setId(keys.getInt("id"));
            expense.setExpenseStatus(ExpenseStatus.valueOf(keys.getString("expense_status")));
            expense.setDescription(keys.getString("description"));
            expense.setExpenseType(ExpenseType.valueOf(keys.getString("expense_type")));
            expense.setIssuerId(keys.getInt("issuer_id"));
            expense.setExpenseValue(keys.getInt("expense_value"));

            return expense;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExpenseById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
