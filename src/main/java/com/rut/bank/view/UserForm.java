package com.rut.bank.view;

import javax.swing.*;
import java.awt.*;

public class UserForm {
    private JPanel panel;
    private JLabel labelBalance;
    private JButton buttonDeposit;
    private JButton buttonWithdraw;
    private JButton buttonInfo;
    private JButton buttonLogout;
    private JFrame frame;

    public UserForm() {
        panel = new JPanel(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        labelBalance = new JLabel("Balance: 0.00");
        topPanel.add(labelBalance);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonDeposit = new JButton("Deposit");
        buttonWithdraw = new JButton("Withdraw");
        buttonInfo = new JButton("Info");
        buttonLogout = new JButton("Logout");

        centerPanel.add(buttonDeposit);
        centerPanel.add(buttonWithdraw);
        centerPanel.add(buttonInfo);
        centerPanel.add(buttonLogout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        // frame
        frame = new JFrame("Bank - User Panel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // centrowanie okna
        frame.setVisible(true);
    }

    // Gettery do kontrolera
        public JFrame getFrame() { return frame; }
        public JLabel getLabelBalance() { return labelBalance; }
        public JButton getButtonDeposit() { return buttonDeposit; }
        public JButton getButtonWithdraw() { return buttonWithdraw; }
        public JButton getButtonInfo() { return buttonInfo; }
        public JButton getButtonLogout() { return buttonLogout; }
    }

