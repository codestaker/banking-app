package com.bank.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String accountNumber;
    protected String customerId;
    protected double balance;
    protected List<Transaction> transactions;

    public Account(String accountNumber, String customerId, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        addTransaction("Initial Deposit", initialDeposit);
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getCustomerId() { return customerId; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposit", amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            addTransaction("Withdrawal", -amount);
        } else {
            // In a real app, throw an exception
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    protected void addTransaction(String type, double amount) {
        Transaction transaction = new Transaction(type, amount, this.balance);
        this.transactions.add(transaction);
    }

    public String getFormattedBalance() {
        NumberFormat ngnFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
        return ngnFormat.format(this.balance);
    }

    public abstract String getAccountType();

    @Override
    public String toString() {
        return "Account [Number=" + accountNumber + ", Balance=" + getFormattedBalance() + ", Type=" + getAccountType() + "]";
    }
}
