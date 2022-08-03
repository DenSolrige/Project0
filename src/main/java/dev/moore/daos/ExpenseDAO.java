package dev.moore.daos;

import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;

import java.util.List;

public interface ExpenseDAO {

    //Create
    Expense createExpense(Expense expense);

    //Read
    List<Expense> getAllExpenses();
    List<Expense> getExpensesByStatus(ExpenseStatus status);
    List<Expense> getExpensesByIssuerId(int issuerId);
    Expense getExpenseById(int id);

    //Update
    Expense updateExpense(int id, Expense expense);
    Expense approveExpenseStatus(int id);
    Expense denyExpenseStatus(int id);

    //Delete
    boolean deleteExpenseById(int id);

}
