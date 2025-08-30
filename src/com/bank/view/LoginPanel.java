package com.bank.view;

import com.bank.controller.BankController;
import com.bank.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private BankController controller;
    private JTextField customerIdField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame mainFrame, BankController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Welcome to Universal Bank"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("Customer ID:"), gbc);

        gbc.gridx = 1;
        customerIdField = new JTextField(15);
        add(customerIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel, gbc);

        loginButton.addActionListener(this::performLogin);
        registerButton.addActionListener(this::showRegisterDialog);
    }

    private void performLogin(ActionEvent e) {
        String customerId = customerIdField.getText();
        String password = new String(passwordField.getPassword());
        if (controller.login(customerId, password)) {
            mainFrame.showPanel("Dashboard");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showRegisterDialog(ActionEvent e) {
        // Simple register dialog
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField email = new JTextField();
        JTextField phone = new JTextField();
        JPasswordField password = new JPasswordField();
        
        Object[] message = {
            "First Name:", firstName,
            "Last Name:", lastName,
            "Email:", email,
            "Phone:", phone,
            "Password:", password
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Register", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Customer newCustomer = controller.registerCustomer(firstName.getText(), lastName.getText(), email.getText(), phone.getText(), new String(password.getPassword()));
            JOptionPane.showMessageDialog(this, "Registration successful! Your new Customer ID is: " + newCustomer.getCustomerId());
        }
    }
}
