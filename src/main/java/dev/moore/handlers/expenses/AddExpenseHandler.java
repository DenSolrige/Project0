package dev.moore.handlers.expenses;


import com.google.gson.Gson;
import dev.moore.api.Expense;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class AddExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        String json = ctx.body();
        Expense expense = gson.fromJson(json, Expense.class);
        Expense registeredExpense = App.expenseService.RegisterExpense(expense);
        if (registeredExpense == null){
            ctx.status(422);
            ctx.result("Expense add failed, make sure that the value is non-negative");
            return;
        }else{
            String registeredJson = gson.toJson(registeredExpense);
            ctx.status(201);
            ctx.result(registeredJson);
        }
    }
}
