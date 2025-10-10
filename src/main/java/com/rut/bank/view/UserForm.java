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
        // główny panel z marginesem
        panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // górny pasek z balansem
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        labelBalance = new JLabel("Balance: 0.00");
        labelBalance.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(labelBalance);

        // środkowy panel z przyciskami
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonDeposit = new JButton("Deposit");
        buttonWithdraw = new JButton("Withdraw");
        buttonInfo = new JButton("Info");
        buttonLogout = new JButton("Logout");

        // większe przyciski
        buttonDeposit.setPreferredSize(new Dimension(150, 50));
        buttonWithdraw.setPreferredSize(new Dimension(150, 50));
        buttonInfo.setPreferredSize(new Dimension(150, 50));
        buttonLogout.setPreferredSize(new Dimension(150, 50));

        centerPanel.add(buttonDeposit);
        centerPanel.add(buttonWithdraw);
        centerPanel.add(buttonInfo);
        centerPanel.add(buttonLogout);

        // składamy całość
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        // frame
        frame = new JFrame("Bank - User Panel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setSize(400, 300); // większe okno
        frame.setLocationRelativeTo(null); // centrowanie
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

