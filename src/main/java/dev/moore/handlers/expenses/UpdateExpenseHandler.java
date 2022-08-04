package dev.moore.handlers.expenses;

import com.google.gson.Gson;
import dev.moore.entities.Expense;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson = new Gson();
        String json = ctx.body();
        Expense expense = gson.fromJson(json, Expense.class); // could throw exception if user doesn't use right values for enums
        expense.setId(id);
        if(expense.getExpenseType() == null){
            ctx.status(422);
            ctx.result("Expense update failed, make sure that ExpenseType is either LODGING, TRAVEL, FOOD, or MISC");
            return;
        }

        Expense registeredExpense = App.expenseService.updateExpense(expense);
        if (registeredExpense == null){
            ctx.status(422);
            ctx.result("Expense with given id not found");
        }else if (registeredExpense.getExpenseValue() < 0) {
            ctx.status(422);
            ctx.result("Expense update failed, make sure that the value is non-negative");
        }else {
            String registeredJson = gson.toJson(registeredExpense);
            ctx.status(201);
            ctx.result(registeredJson);
        }
    }
}
