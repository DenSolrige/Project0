package dev.moore.entities;

public class Expense {

    private int id;
    private ExpenseStatus expenseStatus = ExpenseStatus.Pending;
    private String description;
    private ExpenseType expenseType;
    private int issuerId;
    private int expenseValue;

    public Expense(){}
    public Expense(String description, ExpenseType expenseType, int issuerId, int expenseValue) {
        this.description = description;
        this.expenseType = expenseType;
        this.issuerId = issuerId;
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

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
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
                ", expenseType=" + expenseType +
                ", issuerId=" + issuerId +
                ", expenseValue=" + expenseValue +
                '}';
    }
}
