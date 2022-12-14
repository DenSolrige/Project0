package dev.moore.services;

import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;
import dev.moore.daos.ExpenseDAO;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO){
        this.expenseDAO = expenseDAO;
    }

    @Override
    public Expense registerExpense(Expense expense) {
        if(expense.getExpenseValue() < 0){
            return null;
        }else{
            return this.expenseDAO.createExpense(expense);
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public List<Expense> getExpensesByStatus(ExpenseStatus status) {
        return this.expenseDAO.getExpensesByStatus(status);
    }

    @Override
    public List<Expense> getExpenseByIssuerId(int id) {
        return this.expenseDAO.getExpensesByIssuerId(id);
    }

    @Override
    public Expense getExpenseById(int id) {
        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public Expense approveExpenseStatus(int id) {
        if(this.expenseDAO.getExpenseById(id) == null){
            return null;
        }
        if(this.expenseDAO.getExpenseById(id).getExpenseStatus() != ExpenseStatus.Pending) {
            return null;
        }else{
            return this.expenseDAO.approveExpenseStatus(id);
        }
    }

    @Override
    public Expense denyExpenseStatus(int id) {
        if(this.expenseDAO.getExpenseById(id) == null){
            return null;
        }
        if(this.expenseDAO.getExpenseById(id).getExpenseStatus() != ExpenseStatus.Pending) {
            return null;
        }else{
            return this.expenseDAO.denyExpenseStatus(id);
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        if(this.expenseDAO.getExpenseById(expense.getId()) == null){
            return null;
        }
        if(this.expenseDAO.getExpenseById(expense.getId()).getExpenseStatus() != ExpenseStatus.Pending
            || expense.getExpenseValue() < 0){ //move expense value check to handler
            return null;
        }
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public boolean deleteExpenseById(int id) {
        Expense expense =  this.expenseDAO.getExpenseById(id);
        if(expense == null){
            return false;
        }
        if(this.expenseDAO.getExpenseById(id).getExpenseStatus() != ExpenseStatus.Pending) {
            return false;
        }else {
            return this.expenseDAO.deleteExpenseById(id);
        }
    }
}
