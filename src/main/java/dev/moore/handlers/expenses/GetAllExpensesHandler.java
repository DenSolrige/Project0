package dev.moore.handlers.expenses;

import com.google.gson.Gson;
import dev.moore.entities.Expense;
import dev.moore.entities.ExpenseStatus;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllExpensesHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String status = ctx.queryParam("status");
        List<Expense> expenseList;
        if (status != null){
            expenseList = App.expenseService.getExpensesByStatus(ExpenseStatus.valueOf(status));
        }else {
            expenseList = App.expenseService.getAllExpenses();
        }
        Gson gson = new Gson();
        String json = gson.toJson(expenseList);
        ctx.result(json);
    }
}
