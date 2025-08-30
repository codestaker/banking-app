package com.bank.model;

public class CurrentAccount extends Account {
    private static final long serialVersionUID = 1L;
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String customerId, double initialDeposit) {
        super(accountNumber, customerId, initialDeposit);
        this.overdraftLimit = 0; // Default overdraft limit
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            addTransaction("Withdrawal", -amount);
        } else {
            System.out.println("Overdraft limit exceeded or invalid amount.");
        }
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
