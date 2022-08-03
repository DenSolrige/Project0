package dev.moore.daos;

import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDaoLocal implements ExpenseDAO{

    private final Map<Integer,Expense> expenseTable = new HashMap<>();
    private int idMaker = 1;

    @Override
    public Expense createExpense(Expense expense) {
        expense.setId(idMaker++);
        expenseTable.put(expense.getId(),expense);
        return expense;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenseTable.values());
    }

    @Override
    public List<Expense> getExpensesByStatus(ExpenseStatus status) {
        List<Expense> expenseList = new ArrayList<>(expenseTable.values());
        List<Expense> expenseListOfStatus = new ArrayList<>();
        for (Expense expense : expenseList){
            if(expense.getExpenseStatus() == status){
                expenseListOfStatus.add(expense);
            }
        }
        return expenseListOfStatus;
    }

    @Override
    public List<Expense> getExpensesByIssuerId(int issuerId) {
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        return expenseTable.get(id);
    }

    @Override
    public Expense updateExpense(int id, Expense expense) {
        expense.setId(id);
        expenseTable.put(id,expense);
        return expenseTable.get(expense.getId());
    }

    @Override
    public Expense denyExpenseStatus(int id) {
        Expense expense = expenseTable.get(id);
        if(expense == null){
            return null;
        }
        expense.setExpenseStatus(ExpenseStatus.Denied);
        expenseTable.put(id,expense);
        return expenseTable.put(id,expense);
    }

    @Override
    public Expense approveExpenseStatus(int id) {
        Expense expense = expenseTable.get(id);
        if(expense == null){
            return null;
        }
        expense.setExpenseStatus(ExpenseStatus.Approved);
        expenseTable.put(id,expense);
        return expenseTable.put(id,expense);
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense expense = expenseTable.remove(id);
        return expense != null;
    }
}
