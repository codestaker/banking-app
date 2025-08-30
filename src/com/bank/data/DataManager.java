package com.bank.data;

import com.bank.model.Account;
import com.bank.model.Customer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static final String CUSTOMER_FILE = "data/customers.dat";
    private static final String ACCOUNT_FILE = "data/accounts.dat";

    public void saveData(Map<String, Customer> customers, Map<String, Account> accounts) {
        try {
            // Ensure the data directory exists
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            try (ObjectOutputStream oosCustomers = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE));
                 ObjectOutputStream oosAccounts = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE))) {
                oosCustomers.writeObject(customers);
                oosAccounts.writeObject(accounts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Customer> loadCustomers() {
        File file = new File(CUSTOMER_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Map<String, Customer>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Account> loadAccounts() {
        File file = new File(ACCOUNT_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Map<String, Account>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }
}
