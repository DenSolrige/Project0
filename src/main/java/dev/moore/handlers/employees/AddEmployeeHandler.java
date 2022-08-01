package dev.moore.handlers.employees;

import com.google.gson.Gson;
import dev.moore.api.Employee;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class AddEmployeeHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        String json = ctx.body();
        Employee employee = gson.fromJson(json,Employee.class);
        Employee registeredEmployee = App.employeeService.registerEmployee(employee);
        String registeredJson = gson.toJson(registeredEmployee);
        ctx.status(201);
        ctx.result(registeredJson);
    }
}
