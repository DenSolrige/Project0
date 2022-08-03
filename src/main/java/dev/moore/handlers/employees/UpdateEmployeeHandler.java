package dev.moore.handlers.employees;

import com.google.gson.Gson;
import dev.moore.entities.Employee;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson = new Gson();
        String json = ctx.body();
        Employee employee = gson.fromJson(json,Employee.class);
        Employee updatedEmployee = App.employeeService.updateEmployee(id,employee);
        if (updatedEmployee == null){
            ctx.status(404);
            ctx.result("Employee with given id not found");
            return;
        }
        String updatedJson = gson.toJson(updatedEmployee);
        ctx.result(updatedJson);
    }
}
