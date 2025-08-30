package com.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passwordHash; // Store hash, not plain password
    private List<String> accountNumbers;

    public Customer(String customerId, String firstName, String lastName, String email, String phoneNumber, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = hashPassword(password);
        this.accountNumbers = new ArrayList<>();
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<String> getAccountNumbers() { return accountNumbers; }

    public void addAccountNumber(String accountNumber) {
        this.accountNumbers.add(accountNumber);
    }

    public boolean checkPassword(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }

    // Simple hashing function for demonstration. In a real app, use a strong library like BCrypt.
    private String hashPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", Name=" + firstName + " " + lastName + "]";
    }
}
