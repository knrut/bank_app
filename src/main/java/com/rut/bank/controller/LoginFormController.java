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
            JOptionPane.showMessageDialog(loginForm.getFrame(), "Logged in as: " + login);
            loginForm.getFrame().dispose();
            switch (service.getLoggedInClient().getRole()) {
                case ADMIN -> new AdminFormController(service, service.getBankAccountRepository(),
                        service.getTransactionRepository(), service.getClientRepository());
                case USER -> new BankAccountController(service);
            }
        } else {
            JOptionPane.showMessageDialog(loginForm.getFrame(), "Incorrect login or password");
            loginForm.getTextFieldLogin().setText("");
            loginForm.getTextFieldPassword().setText("");
        }
    }

    private void onRegister() {
        loginForm.getFrame().dispose();
        new RegisterClientFormController(service);
    }

    private void onCreateClient() {
        loginForm.getFrame().dispose();
        new CreateClientFormController(service);
    }
}
