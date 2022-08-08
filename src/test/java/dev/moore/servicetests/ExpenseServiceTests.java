package dev.moore.servicetests;

import dev.moore.daos.ExpenseDaoPostgres;
import dev.moore.entities.Employee;
import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;
import dev.moore.entities.ExpenseType;
import dev.moore.services.ExpenseService;
import dev.moore.services.ExpenseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ExpenseServiceTests {

    //expenses having only one issuer is constrained by
    //the database expenses being able to only have one issuer
    //Expense objects also can only have one issuer

    @Test
    void expenses_start_as_pending_test(){
        ExpenseDaoPostgres expenseDaoPostgres = Mockito.mock(ExpenseDaoPostgres.class);
        Expense expense = new Expense("Bought a taco", ExpenseType.Food,1,7);
        Mockito.when(expenseDaoPostgres.createExpense(expense)).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(expenseDaoPostgres);

        Assertions.assertEquals("Pending",expenseService.registerExpense(expense).getExpenseStatus().toString());
    }

    @Test
    void approved_expenses_cannot_be_deleted(){
        ExpenseDaoPostgres expenseDaoPostgres = Mockito.mock(ExpenseDaoPostgres.class);
        Expense expense = new Expense("Bought a taco", ExpenseType.Food,1,7);
        expense.setExpenseStatus(ExpenseStatus.Approved);
        Mockito.when(expenseDaoPostgres.deleteExpenseById(1)).thenReturn(true); //show that the service will still return false even if DAO doesn't
        Mockito.when(expenseDaoPostgres.getExpenseById(1)).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(expenseDaoPostgres);

        Assertions.assertFalse(expenseService.deleteExpenseById(1));
    }

    @Test
    void approved_expenses_cannot_be_edited(){
        ExpenseDaoPostgres expenseDaoPostgres = Mockito.mock(ExpenseDaoPostgres.class);
        Expense expense = new Expense("Bought a taco", ExpenseType.Food,1,7);
        expense.setExpenseStatus(ExpenseStatus.Approved);
        Mockito.when(expenseDaoPostgres.updateExpense(expense)).thenReturn(expense); //show that the service will still return false even if DAO doesn't
        Mockito.when(expenseDaoPostgres.getExpenseById(expense.getId())).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(expenseDaoPostgres);

        Assertions.assertNull(expenseService.updateExpense(expense));
    }

    @Test
    void expenses_cant_be_negative(){
        ExpenseDaoPostgres expenseDaoPostgres = Mockito.mock(ExpenseDaoPostgres.class);
        Expense expense = new Expense("Bought a taco", ExpenseType.Food,1,-999);
        Mockito.when(expenseDaoPostgres.createExpense(expense)).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(expenseDaoPostgres);

        Assertions.assertNull(expenseService.registerExpense(expense));
    }
}
