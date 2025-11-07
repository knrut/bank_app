package com.rut.bank.controller;

import com.rut.bank.model.Service;
import com.rut.bank.model.Nationality;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.CreateClientForm;

import javax.swing.*;
import java.time.LocalDate;

public class CreateClientFormController {
    private final Service service;
    private final CreateClientForm createClientForm;

    public CreateClientFormController(Service service) {
        this.service = service;
        this.createClientForm = new CreateClientForm(this::onLogout);
        control();
    }

    private void control() {
        createClientForm.getCreateButton().addActionListener(e -> onCreateClient());
        createClientForm.getBackButton().addActionListener(e -> onLogout());
    }

    private void onCreateClient() {
        String nationalId = createClientForm.getNationalId();
        String firstName = createClientForm.getFirstNameField().getText();
        String lastName = createClientForm.getLastNameField().getText();
        LocalDate dob = createClientForm.getDateOfBirth();
        Nationality nat = createClientForm.getSelectedNationality();

        boolean validInputFormat = DataValidator.validateNationalId(nationalId)
                && DataValidator.validateFirstName(firstName)
                && DataValidator.validateLastName(lastName)
                && DataValidator.validateDithOfBirth(dob);

        if (!validInputFormat) {
            showMessage("Invalid data format");
            //resetFields();
            return;
        }

        if (service.createClientProfile(nationalId, firstName, lastName, dob, nat)) {
            showMessage(
                    "Client Profile successfully created!\n" +
                    "Nationality:\t" + nat + "\n" +
                    "National ID:\t" + nationalId + "\n" +
                    "First Name:\t" + firstName + "\n" +
                    "Last Name:\t" + lastName
            );
        } else {
            showMessage(
                    "A customer with the information " +
                    "you provided already exists. If you think this is an error, please contact your bank branch.");
        }
    }

    private void onLogout() {
        disposeWindow();
        new LoginFormController(service);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(createClientForm.getFrame(), message);
    }

    private void disposeWindow() {
        createClientForm.getFrame().dispose();
    }
}
