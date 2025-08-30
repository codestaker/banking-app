package com.bank.model;

public class SavingsAccount extends Account {
    private static final long serialVersionUID = 1L;
    private static final double INTEREST_RATE = 0.02; // 2% interest rate

    public SavingsAccount(String accountNumber, String customerId, double initialDeposit) {
        super(accountNumber, customerId, initialDeposit);
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        deposit(interest);
        // Overwrite the generic "Deposit" transaction type
        transactions.get(transactions.size() - 1).setType("Interest Applied");
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
