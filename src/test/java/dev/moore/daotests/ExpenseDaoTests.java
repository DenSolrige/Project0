package dev.moore.daotests;

import dev.moore.daos.ExpenseDaoPostgres;
import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;
import dev.moore.entities.ExpenseType;
import dev.moore.daos.ExpenseDAO;
import dev.moore.daos.ExpenseDaoLocal;
import dev.moore.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTests {

    static ExpenseDAO expenseDAO = new ExpenseDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "drop table if exists expense;\n" +
                    "drop table if exists employee;\n" +
                    "\n" +
                    "create table employee(\n" +
                    "\n" +
                    "\tid serial primary key,\n" +
                    "\tfirst_name varchar(100) not null,\n" +
                    "\tlast_name varchar(100) not null\n" +
                    "\t\n" +
                    ");\n" +
                    "\n" +
                    "create table expense(\n" +
                    "\n" +
                    "\tid serial primary key,\n" +
                    "\texpense_status varchar(20) not null,\n" +
                    "\tdescription varchar(400) not null,\n" +
                    "\texpense_type varchar(20) not null,\n" +
                    "\tissuer_id int references employee(id) not null,\n" +
                    "\texpense_value int not null check(expense_value >= 0)\n" +
                    "\n" +
                    ");\n" +
                    "\n" +
                    "insert into employee values(default,'Bob','Bobson');";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_expense_test(){
        Expense expense = new Expense("Bought a burger", ExpenseType.Food,1,7);
        Expense savedExpense = expenseDAO.createExpense(expense);
        Assertions.assertEquals(1,savedExpense.getId());
    }

    @Test
    @Order(2)
    void get_all_expenses_test(){
        List<Expense> expenseList = expenseDAO.getAllExpenses();
        Assertions.assertEquals(1,expenseList.size());
    }

    @Test
    @Order(3)
    void get_expenses_by_status_test(){
        List<Expense> expenseList = expenseDAO.getExpensesByStatus(ExpenseStatus.Pending);
        Assertions.assertEquals(1,expenseList.size());
    }

    @Test
    @Order(4)
    void get_expense_by_issuer_id(){
        List<Expense> expenseList = expenseDAO.getExpensesByIssuerId(1);
        Assertions.assertEquals(1,expenseList.size());
    }

    @Test
    @Order(5)
    void get_expense_by_id_test(){
        Expense expense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(7,expense.getExpenseValue());
    }

    @Test
    @Order(6)
    void update_expense_test(){
        Expense expense = new Expense("Bought a taco",ExpenseType.Food,1,7);
        expense.setId(1);
        Expense updatedExpense = expenseDAO.updateExpense(expense);
        Assertions.assertEquals("Bought a taco",updatedExpense.getDescription());
    }

    @Test
    @Order(7)
    void approve_expense_status_test(){
        Expense updatedExpense = expenseDAO.approveExpenseStatus(1);
        Assertions.assertEquals(ExpenseStatus.Approved,updatedExpense.getExpenseStatus());
    }

    @Test
    @Order(8)
    void deny_expense_status_test(){
        Expense updatedExpense = expenseDAO.denyExpenseStatus(1);
        Assertions.assertEquals(ExpenseStatus.Denied,updatedExpense.getExpenseStatus());
    }

    @Test
    @Order(9)
    void delete_expense_test(){
        boolean result = expenseDAO.deleteExpenseById(1);
        Assertions.assertTrue(result);
    }
}
