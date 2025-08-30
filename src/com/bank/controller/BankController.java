package com.bank.controller;

import com.bank.data.DataManager;
import com.bank.model.*;

import java.util.Map;
import java.util.UUID;

public class BankController {
    private Map<String, Customer> customers;
    private Map<String, Account> accounts;
    private DataManager dataManager;
    private Customer currentCustomer;

    public BankController() {
        this.dataManager = new DataManager();
        this.customers = dataManager.loadCustomers();
        this.accounts = dataManager.loadAccounts();
    }

    public Customer registerCustomer(String firstName, String lastName, String email, String phone, String password) {
        String customerId = "CUST" + UUID.randomUUID().toString().substring(0, 4);
        Customer customer = new Customer(customerId, firstName, lastName, email, phone, password);
        customers.put(customerId, customer);
        saveData();
        return customer;
    }

    public boolean login(String customerId, String password) {
        Customer customer = customers.get(customerId);
        if (customer != null && customer.checkPassword(password)) {
            currentCustomer = customer;
            return true;
        }
        return false;
    }

    public void logout() {
        currentCustomer = null;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public Account openAccount(String customerId, String accountType, double initialDeposit) {
        String accountNumber = "ACC" + UUID.randomUUID().toString().substring(0, 6);
        Account account = null;
        if ("Savings".equalsIgnoreCase(accountType)) {
            account = new SavingsAccount(accountNumber, customerId, initialDeposit);
        } else if ("Current".equalsIgnoreCase(accountType)) {
            account = new CurrentAccount(accountNumber, customerId, initialDeposit);
        }

        if (account != null) {
            accounts.put(accountNumber, account);
            customers.get(customerId).addAccountNumber(accountNumber);
            saveData();
        }
        return account;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            accounts.put(accountNumber, account); // Update the map
            saveData();
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            accounts.put(accountNumber, account); // Update the map
            saveData();
        }
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        Account source = getAccount(fromAccount);
        Account destination = getAccount(toAccount);
        if (source != null && destination != null && source.getBalance() >= amount) {
            source.withdraw(amount);
            destination.deposit(amount);
            accounts.put(fromAccount, source);
            accounts.put(toAccount, destination);
            saveData();
        }
    }

    private void saveData() {
        dataManager.saveData(customers, accounts);
    }
}
