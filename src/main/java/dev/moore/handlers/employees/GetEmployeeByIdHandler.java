package dev.moore.handlers.employees;

import com.google.gson.Gson;
import dev.moore.entities.Employee;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.getEmployeeById(id);
        if(employee == null){
            ctx.status(404);
            ctx.result("Employee with given id not found");
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.result(json);
    }
}
