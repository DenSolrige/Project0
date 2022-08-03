package dev.moore.handlers.expenses;

import com.google.gson.Gson;
import dev.moore.entities.Expense;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.getExpenseById(id);
        if(expense == null){
            ctx.status(404);
            ctx.result("Expense with given id not found");
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(expense);
        ctx.result(json);
    }
}
