package com.rut.bank.view;

import javax.swing.*;

public class LoginForm {
    private JPanel panel;
    private JTextField textFieldLogin;
    private JPasswordField textFieldPassword;
    private JButton buttonLogin;
    private JButton buttonRegister;
    private JFrame frame;

    public LoginForm() {
        frame = new JFrame("Bank - Login");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getTextFieldLogin() {
        return textFieldLogin;
    }

    public JPasswordField getTextFieldPassword() {
        return textFieldPassword;
    }

    public JButton getButtonLogin() {
        return buttonLogin;
    }

    public JButton getButtonRegister() {
        return buttonRegister;
    }


}
