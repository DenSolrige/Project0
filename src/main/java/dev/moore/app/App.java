package dev.moore.app;

import dev.moore.daos.EmployeeDaoLocal;
import dev.moore.daos.ExpenseDaoLocal;
import dev.moore.handlers.employees.*;
import dev.moore.handlers.expenses.AddExpenseHandler;
import dev.moore.services.EmployeeService;
import dev.moore.services.EmployeeServiceImpl;
import dev.moore.services.ExpenseService;
import dev.moore.services.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoLocal());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoLocal());

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        AddEmployeeHandler addEmployeeHandler = new AddEmployeeHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
        DeleteEmployeeByIdHandler deleteEmployeeByIdHandler = new DeleteEmployeeByIdHandler();

        AddExpenseHandler addExpenseHandler = new AddExpenseHandler();

        app.post("/employees",addEmployeeHandler); //returns 201
        app.get("/employees",getAllEmployeesHandler);
        app.get("/employees/{id}",getEmployeeByIdHandler); //returns 404 if employee not found
        app.put("/employees/{id}",updateEmployeeHandler); //returns 404 if employee not found
        app.delete("/employees/{id}",deleteEmployeeByIdHandler); //returns 404 if employee not found

        app.post("/expenses",addExpenseHandler); //returns 201
//        app.get("/expenses",null);
//        app.get("/expenses?status=pending",null); //also can get status approved or denied
//        app.get("/expenses/{id}",null); //returns 404 if expense not found
//        app.put("/expenses/{id}",null); //returns 404 if expense not found
//        app.patch("/expenses/{id}/approve",null); //returns 404 if expense not found
//        app.patch("/expenses/{id}/deny",null); //returns 404 if expense not found
//        app.delete("/expenses/{id}",null); //returns 404 if expense not found or returns 422 if expense is already approved or denied
//
//        app.get("/employees/{id}/expenses",null); //returns expenses for employees 120
//        app.post("/employees/{id}/expenses",null); //adds an expense to employee 120

        app.start();
    }
}
