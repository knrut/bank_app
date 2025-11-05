package com.rut.bank.controller;

import com.rut.bank.model.Service;
import com.rut.bank.repository.ClientRepository;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.LoginForm;
import com.rut.bank.view.RegisterClientForm;

import javax.swing.*;

public class RegisterClientFormController {
    private final Service service;
    private final RegisterClientForm registerClientForm;

    public RegisterClientFormController(Service service) {
        this.service = service;
        this.registerClientForm = new RegisterClientForm(this::onLogout);
        control();
    }

    private void onLogout() {
        registerClientForm.getFrame().dispose();
        new LoginFormController(service);
    }

    private void control() {
        registerClientForm.getRegisterButton().addActionListener(a -> onRegister());
        registerClientForm.getBackButton().addActionListener(c -> onLogout());
    }

    private void onRegister() {
        String nationalId = registerClientForm.getNationalId();
        String firstName = registerClientForm.getFirstName();
        String lastName = registerClientForm.getLastName();
        String login = registerClientForm.getLogin();
        String password = new String(registerClientForm.getPassword());


        if (DataValidator.validateNationalId(nationalId) && DataValidator.validateFirstName(firstName)
                && DataValidator.validateLastName(lastName) && DataValidator.validateLogin(login)
                && DataValidator.validatePassword(password)) {
            if (service.isMatchClient(nationalId, firstName, lastName)) {
                if (service.registerUser(login, password, nationalId)) {
                    JOptionPane.showMessageDialog(registerClientForm.getFrame(), "User registered: " + login);
                    registerClientForm.getFrame().dispose();
                    new LoginFormController(service);
                }
                else {
                    JOptionPane.showMessageDialog(registerClientForm.getFrame(), "Login already taken!");
                    resetFields();
                }
            } else {
                JOptionPane.showMessageDialog(registerClientForm.getFrame(), "No Client found with the given data");
                resetFields();
            }
        }
        else {
            JOptionPane.showMessageDialog(registerClientForm.getFrame(), "Invalid Data Format");
            resetFields();
        }
    }
    private void resetFields() {
        registerClientForm.getNationalIdField().setText("");
        registerClientForm.getFirstNameField().setText("");
        registerClientForm.getLastNameField().setText("");
        registerClientForm.getLoginField().setText("");
        registerClientForm.getPasswordField().setText("");
    }
}
