package com.rut.bank.view;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private JPanel panel;
    private JTextField textFieldLogin;
    private JPasswordField textFieldPassword;
    private JButton buttonLogin;
    private JButton buttonRegister;
    private JButton buttonCreateProfile;
    private JFrame frame;

    public LoginForm() {
        frame = new JFrame("Bank - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL; // domyślnie rozciągaj w poziomie

        Font biggerFont = new Font("Arial", Font.PLAIN, 18);

        // Login label
        c.gridx = 0; c.gridy = 0;
        c.weightx = 0; // label nie musi się rozszerzać
        JLabel loginLabel = new JLabel("Login:", SwingConstants.RIGHT);
        loginLabel.setFont(biggerFont);
        panel.add(loginLabel, c);

        // Login field
        c.gridx = 1; c.gridy = 0;
        c.weightx = 1; // pole może się rozszerzać
        textFieldLogin = new JTextField();
        textFieldLogin.setFont(biggerFont);
        panel.add(textFieldLogin, c);

        // Password label
        c.gridx = 0; c.gridy = 1;
        c.weightx = 0;
        JLabel passLabel = new JLabel("Password:", SwingConstants.RIGHT);
        passLabel.setFont(biggerFont);
        panel.add(passLabel, c);

        // Password field
        c.gridx = 1; c.gridy = 1;
        c.weightx = 1;
        textFieldPassword = new JPasswordField();
        textFieldPassword.setFont(biggerFont);
        panel.add(textFieldPassword, c);

        // Login button
        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 2; // rozciągnij na całą szerokość
        c.weightx = 1;
        buttonLogin = new JButton("Login");
        buttonLogin.setFont(biggerFont);
        panel.add(buttonLogin, c);

        // Register button
        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 1;
        buttonRegister = new JButton("Register");
        buttonRegister.setFont(biggerFont);
        panel.add(buttonRegister, c);

        // Create Client Profile button
        c.gridx = 0; c.gridy = 4;
        c.gridwidth = 2;
        c.weightx = 1;
        buttonCreateProfile = new JButton("Create Client Profile");
        buttonCreateProfile.setFont(biggerFont);
        buttonCreateProfile.setForeground(new Color(0, 102, 204));
        panel.add(buttonCreateProfile, c);

        frame.setContentPane(panel);
        frame.setSize(800, 400);  // duże okno
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public JFrame getFrame() { return frame; }
    public JTextField getTextFieldLogin() { return textFieldLogin; }
    public JPasswordField getTextFieldPassword() { return textFieldPassword; }
    public JButton getButtonLogin() { return buttonLogin; }
    public JButton getButtonRegister() { return buttonRegister; }
}
