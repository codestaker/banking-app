package com.bank.view;

import com.bank.controller.BankController;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private MainFrame mainFrame;
    private BankController controller;
    private JLabel welcomeLabel;
    private JTable accountsTable;
    private DefaultTableModel tableModel;

    public DashboardPanel(MainFrame mainFrame, BankController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));

        welcomeLabel = new JLabel("Welcome, ");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        // Accounts Table
        tableModel = new DefaultTableModel(new String[]{"Account Number", "Type", "Balance"}, 0);
        accountsTable = new JTable(tableModel);
        add(new JScrollPane(accountsTable), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton statementButton = new JButton("View Statement");
        JButton openAccountButton = new JButton("Open New Account");
        JButton logoutButton = new JButton("Logout");
        
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(statementButton);
        buttonPanel.add(openAccountButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        depositButton.addActionListener(e -> performDeposit());
        withdrawButton.addActionListener(e -> performWithdrawal());
        transferButton.addActionListener(e -> performTransfer());
        statementButton.addActionListener(e -> viewStatement());
        openAccountButton.addActionListener(e -> openNewAccount());
        logoutButton.addActionListener(e -> {
            controller.logout();
            mainFrame.showPanel("Login");
        });
    }

    public void refreshDashboard() {
        Customer customer = controller.getCurrentCustomer();
        if (customer != null) {
            welcomeLabel.setText("Welcome, " + customer.getFirstName() + " " + customer.getLastName());
            tableModel.setRowCount(0); // Clear existing rows
            for (String accNum : customer.getAccountNumbers()) {
                Account acc = controller.getAccount(accNum);
                tableModel.addRow(new Object[]{acc.getAccountNumber(), acc.getAccountType(), acc.getFormattedBalance()});
            }
        }
    }

    private String getSelectedAccountNumber() {
        int selectedRow = accountsTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (String) tableModel.getValueAt(selectedRow, 0);
        }
        JOptionPane.showMessageDialog(this, "Please select an account first.", "No Account Selected", JOptionPane.WARNING_MESSAGE);
        return null;
    }

    private void performDeposit() {
        String accountNumber = getSelectedAccountNumber();
        if (accountNumber == null) return;

        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        try {
            double amount = Double.parseDouble(amountStr);
            controller.deposit(accountNumber, amount);
            refreshDashboard();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performWithdrawal() {
        String accountNumber = getSelectedAccountNumber();
        if (accountNumber == null) return;

        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        try {
            double amount = Double.parseDouble(amountStr);
            controller.withdraw(accountNumber, amount);
            refreshDashboard();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performTransfer() {
        String fromAccount = getSelectedAccountNumber();
        if (fromAccount == null) return;

        String toAccount = JOptionPane.showInputDialog(this, "Enter destination account number:");
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to transfer:");
        try {
            double amount = Double.parseDouble(amountStr);
            controller.transfer(fromAccount, toAccount, amount);
            refreshDashboard();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStatement() {
        String accountNumber = getSelectedAccountNumber();
        if (accountNumber == null) return;

        Account account = controller.getAccount(accountNumber);
        JTable statementTable = new JTable();
        DefaultTableModel statementModel = new DefaultTableModel(new String[]{"Date", "Type", "Amount", "Balance"}, 0);
        statementTable.setModel(statementModel);

        for (Transaction t : account.getTransactions()) {
            statementModel.addRow(new Object[]{t.getFormattedTimestamp(), t.getType(), t.getFormattedAmount(), t.getFormattedBalanceAfter()});
        }

        JOptionPane.showMessageDialog(this, new JScrollPane(statementTable), "Account Statement", JOptionPane.PLAIN_MESSAGE);
    }

    private void openNewAccount() {
        String[] accountTypes = {"Savings", "Current"};
        String accountType = (String) JOptionPane.showInputDialog(this, "Choose account type:",
                "Open New Account", JOptionPane.QUESTION_MESSAGE, null, accountTypes, accountTypes[0]);

        if (accountType != null) {
            String initialDepositStr = JOptionPane.showInputDialog(this, "Enter initial deposit amount:");
            try {
                double amount = Double.parseDouble(initialDepositStr);
                controller.openAccount(controller.getCurrentCustomer().getCustomerId(), accountType, amount);
                refreshDashboard();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
