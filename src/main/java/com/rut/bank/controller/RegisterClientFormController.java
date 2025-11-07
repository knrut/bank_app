package com.rut.bank.controller;

import com.rut.bank.model.Service;
import com.rut.bank.util.DataValidator;
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

        boolean validInputFormat = DataValidator.validateNationalId(nationalId)
                && DataValidator.validateFirstName(firstName)
                && DataValidator.validateLastName(lastName)
                && DataValidator.validateLogin(login)
                && DataValidator.validatePassword(password);

        if (!validInputFormat) {
            showMessage("Invalid data format");
            resetFields();
            return;
        }

        if (!service.doesClientDataMatch(nationalId, firstName, lastName)) {
            showMessage("No client found with the given data");
            resetFields();
            return;
        }

        if (service.registerUser(nationalId, login, password)) {
            showMessage(
                    "Client registered successfully:\n" +
                            "National ID: " + nationalId + "\n" +
                            "First Name: " + firstName + "\n" +
                            "Last Name: " + lastName + "\n" +
                            "Login: " + login
            );
            disposeWindow();
            new LoginFormController(service);
        }


    }

    private void onLogout() {
        disposeWindow();
        new LoginFormController(service);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(registerClientForm.getFrame(), message);
    }

    private void resetFields() {
        registerClientForm.getNationalIdField().setText("");
        registerClientForm.getFirstNameField().setText("");
        registerClientForm.getLastNameField().setText("");
        registerClientForm.getLoginField().setText("");
        registerClientForm.getPasswordField().setText("");
    }

    private void disposeWindow() {
        registerClientForm.getFrame().dispose();
    }
}
