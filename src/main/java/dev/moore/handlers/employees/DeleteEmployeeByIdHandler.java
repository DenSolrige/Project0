package dev.moore.handlers.employees;

import dev.moore.app.App;
import dev.moore.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.getEmployeeById(id);
        if(employee == null){
            ctx.status(404);
            ctx.result("No employee found with id "+id);
            return;
        }
        boolean result = App.employeeService.deleteEmployeeById(id);
        ctx.status(204);
        ctx.result("Successfully deleted employee with id "+id);
    }
}
