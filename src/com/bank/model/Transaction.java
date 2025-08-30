package com.bank.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private double amount;
    private double balanceAfterTransaction;
    private LocalDateTime timestamp;

    public Transaction(String type, double amount, double balanceAfterTransaction) {
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; } // Added setter
    public double getAmount() { return amount; }
    public double getBalanceAfterTransaction() { return balanceAfterTransaction; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public String getFormattedAmount() {
        NumberFormat ngnFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
        return ngnFormat.format(this.amount);
    }

    public String getFormattedBalanceAfter() {
        NumberFormat ngnFormat = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
        return ngnFormat.format(this.balanceAfterTransaction);
    }

    public String getFormattedTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("Transaction [Date=%s, Type=%s, Amount=%s, Balance=%s]",
                getFormattedTimestamp(), type, getFormattedAmount(), getFormattedBalanceAfter());
    }
}
