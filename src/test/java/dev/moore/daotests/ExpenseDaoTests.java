package dev.moore.daotests;

import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;
import dev.moore.entities.ExpenseType;
import dev.moore.daos.ExpenseDAO;
import dev.moore.daos.ExpenseDaoLocal;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTests {

    static ExpenseDAO expenseDAO = new ExpenseDaoLocal();

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
    void get_expense_by_id_test(){
        Expense expense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(7,expense.getExpenseValue());
    }

    @Test
    @Order(5)
    void update_expense_test(){
        Expense expense = new Expense("Bought a taco",ExpenseType.Food,1,7);
        Expense updatedExpense = expenseDAO.updateExpense(1,expense);
        Assertions.assertEquals("Bought a taco",updatedExpense.getDescription());
    }

    @Test
    @Order(6)
    void approve_expense_status_test(){
        Expense updatedExpense = expenseDAO.approveExpenseStatus(1);
        Assertions.assertEquals(ExpenseStatus.Approved,updatedExpense.getExpenseStatus());
    }

    @Test
    @Order(7)
    void deny_expense_status_test(){
        Expense updatedExpense = expenseDAO.denyExpenseStatus(1);
        Assertions.assertEquals(ExpenseStatus.Denied,updatedExpense.getExpenseStatus());
    }

    @Test
    @Order(8)
    void delete_expense_test(){
        boolean result = expenseDAO.deleteExpenseById(1);
        Assertions.assertTrue(result);
    }
}
