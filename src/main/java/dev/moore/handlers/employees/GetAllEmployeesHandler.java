package dev.moore.handlers.employees;

import com.google.gson.Gson;
import dev.moore.entities.Employee;
import dev.moore.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllEmployeesHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        List<Employee> employeeList = App.employeeService.getAllEmployees();
        Gson gson = new Gson();
        String json = gson.toJson(employeeList);
        ctx.result(json);
    }
}
