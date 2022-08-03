package dev.moore.services;

import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;

import java.util.List;

public interface ExpenseService {

    Expense registerExpense(Expense expense);
    List<Expense> getAllExpenses();
    List<Expense> getExpensesByStatus(ExpenseStatus status);
    Expense getExpenseById(int id);
    Expense approveExpenseStatus(int id);
    Expense denyExpenseStatus(int id);
    Expense updateExpense(int id, Expense expense);
    boolean deleteExpenseById(int id);
}
