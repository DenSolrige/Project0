package dev.moore.handlers.expenses;

import com.google.gson.Gson;
import dev.moore.app.App;
import dev.moore.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetExpenseByIssuerId implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Expense> expenseList = App.expenseService.getExpenseByIssuerId(id);
        Gson gson = new Gson();
        String json = gson.toJson(expenseList);
        ctx.result(json);
    }
}
