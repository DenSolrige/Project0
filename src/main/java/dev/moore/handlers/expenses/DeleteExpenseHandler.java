package dev.moore.handlers.expenses;

import dev.moore.api.Expense;
import dev.moore.api.ExpenseStatus;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.expenseService.deleteExpenseById(id);
        if(result){
            ctx.status(204);
            ctx.result("Expense approved!");
            return;
        }else{
            Expense expense = App.expenseService.getExpenseById(id);
            if(expense.getExpenseStatus() != ExpenseStatus.Pending){
                ctx.status(422);
                ctx.result("Expense with given ID has already been " + expense.getExpenseStatus().toString().toLowerCase());
            }else {
                ctx.status(404);
                ctx.result("Expense with given ID not found");
            }
        }
    }
}
