package com.rut.bank.controller;

import com.rut.bank.model.Service;
import com.rut.bank.view.LoginForm;

import javax.swing.*;

public class LoginFormController {
    private final Service service;
    private final LoginForm loginForm;
    
    public LoginFormController(Service service) {
        this.service = service;
        this.loginForm = new LoginForm();
        control();
    }

    private void control() {
        loginForm.getButtonRegister().addActionListener(e -> onRegister());
        loginForm.getButtonLogin().addActionListener(e -> onLogin());
        loginForm.getButtonCreateProfile().addActionListener(e -> onCreateClient());
    }

    private void onLogin() {
        String login = loginForm.getTextFieldLogin().getText();
        String password = new String(loginForm.getTextFieldPassword().getPassword());

        if (service.loginUser(login, password)) {
            var role = service.getLoggedInClient().getRole();
            showMessage("Logged in as: " + login);
            disposeWindow();
            switch (role) {
                case ADMIN -> new AdminFormController(service, service.getBankAccountRepository(),
                        service.getTransactionRepository(), service.getClientRepository());
                case USER -> new BankAccountController(service);
            }
        } else {
            showMessage("Incorrect login or password");
            resetFields();
        }
    }

    private void onRegister() {
        disposeWindow();
        new RegisterClientFormController(service);
    }

    private void onCreateClient() {
        disposeWindow();
        new CreateClientFormController(service);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(loginForm.getFrame(), message);
    }

    private void resetFields() {
        loginForm.getTextFieldLogin().setText("");
        loginForm.getTextFieldPassword().setText("");
    }

    private void disposeWindow() {
        loginForm.getFrame().dispose();
    }
}
