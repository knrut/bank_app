package com.rut.bank.controller;

import com.rut.bank.model.BankAccountService;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.LoginForm;

import javax.swing.*;

public class LoginFormController {
    private final BankAccountService service;
    private final LoginForm loginForm;
    
    public LoginFormController(BankAccountService service) {
        this.service = service;
        this.loginForm = new LoginForm();
        control();
    }

    private void control() {
        loginForm.getButtonRegister().addActionListener(e -> onRegister());
        loginForm.getButtonLogin().addActionListener(e -> onLogin());
    }

    private void onRegister() {
        String login = loginForm.getTextFieldLogin().getText();
        String password = new String(loginForm.getTextFieldPassword().getPassword());

        if (DataValidator.validateLogin(login) && DataValidator.validatePassword(password)) {
            if (service.registerUser(login, password)) {
                JOptionPane.showMessageDialog(loginForm.getFrame(), "User registered: " + login);
                loginForm.getTextFieldLogin().setText("");
                loginForm.getTextFieldPassword().setText("");
            } else {
                JOptionPane.showMessageDialog(loginForm.getFrame(), "Login already taken!");
                loginForm.getTextFieldLogin().setText("");
                loginForm.getTextFieldPassword().setText("");
            }

        } else {
            JOptionPane.showMessageDialog(loginForm.getFrame(), "Login or password doesn't meet the requirements");
            loginForm.getTextFieldLogin().setText("");
            loginForm.getTextFieldPassword().setText("");
        }
    }

    private void onLogin() {
        String login = loginForm.getTextFieldLogin().getText();
        String password = new String(loginForm.getTextFieldPassword().getPassword());

        if (service.loginUser(login, password)) {
            JOptionPane.showMessageDialog(loginForm.getFrame(), "Logged in as: " + login);
            loginForm.getFrame().dispose();
            switch (service.getLoggedInClient().getRole()) {
                case ADMIN -> new AdminFormController(service.getClientRepository());
                case USER -> new UserFormController(service);
            }
        } else {
            JOptionPane.showMessageDialog(loginForm.getFrame(), "Incorrect login or password");
            loginForm.getTextFieldLogin().setText("");
            loginForm.getTextFieldPassword().setText("");
        }
    }
}
