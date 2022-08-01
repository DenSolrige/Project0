package dev.moore.daos;

import dev.moore.api.Expense;
import dev.moore.api.ExpenseStatus;

import java.util.List;

public interface ExpenseDAO {

    //Create
    Expense createExpense(Expense expense);

    //Read
    List<Expense> getAllExpenses();
    List<Expense> getExpensesByStatus(ExpenseStatus status);
    Expense getExpenseById(int id);

    //Update
    Expense updateExpense(int id, Expense expense);
    Expense approveExpenseStatus(int id);
    Expense denyExpenseStatus(int id);

    //Delete
    boolean deleteExpenseById(int id);

}
