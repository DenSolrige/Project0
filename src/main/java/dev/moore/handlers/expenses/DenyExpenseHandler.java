package dev.moore.handlers.expenses;

import dev.moore.api.Expense;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DenyExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense updatedExpense = App.expenseService.denyExpenseStatus(id);
        if(updatedExpense == null){
            Expense unUpdatedExpense = App.expenseService.getExpenseById(id);
            if(unUpdatedExpense == null) {
                ctx.status(404);
                ctx.result("Expense with given ID not found");
                return;
            }else{
                ctx.status(405);
                ctx.result("Expense with given ID has already been " + unUpdatedExpense.getExpenseStatus().toString().toLowerCase());
                return;
            }
        }else{
            ctx.status(204);
            ctx.result("Expense denied!");
        }
    }
}
