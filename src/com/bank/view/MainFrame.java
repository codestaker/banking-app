package com.bank.view;

import com.bank.controller.BankController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private BankController controller;

    public MainFrame() {
        this.controller = new BankController();
        setTitle("Universal Bank of Group D");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create panels
        LoginPanel loginPanel = new LoginPanel(this, controller);
        DashboardPanel dashboardPanel = new DashboardPanel(this, controller);

        // Add panels to the main panel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(dashboardPanel, "Dashboard");

        add(mainPanel);
        showPanel("Login");
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
        // Refresh dashboard if showing
        if ("Dashboard".equals(panelName)) {
            DashboardPanel dashboard = (DashboardPanel) getPanelByName("Dashboard");
            if (dashboard != null) {
                dashboard.refreshDashboard();
            }
        }
    }

    private Component getPanelByName(String panelName) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp.getClass().getSimpleName().equals(panelName)) {
                return comp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
