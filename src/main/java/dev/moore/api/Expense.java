package dev.moore.api;

public class Expense {

    private int id;

    //private String status = "Pending"; //pending, approved, or denied
    private ExpenseStatus expenseStatus = ExpenseStatus.Pending;

    private String description;
    private ExpenseType expenseType; //determines priority

    //private int issuerId;
    private Employee employee;

    private int expenseValue;

    public Expense(String description, ExpenseType expenseType, int expenseValue) {
        this.description = description;
        this.expenseType = expenseType;
        this.expenseValue = expenseValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExpenseStatus getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(ExpenseStatus expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpenseType getType() {
        return expenseType;
    }

    public void setType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getExpenseValue() {
        return expenseValue;
    }

    public void setExpenseValue(int expenseValue) {
        this.expenseValue = expenseValue;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseStatus=" + expenseStatus +
                ", description='" + description + '\'' +
                ", type=" + expenseType +
                ", employee=" + employee +
                ", expenseValue=" + expenseValue +
                '}';
    }
}
