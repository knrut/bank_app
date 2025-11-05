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
        if (DataValidator.validateNationalId(nationalId) && DataValidator.validateFirstName(firstName)
                && DataValidator.validateLastName(lastName) && DataValidator.validateDithOfBirth(dob)) {
            if (service.createClientProfile(nationalId, firstName, lastName, dob, nat)) {
                JOptionPane.showMessageDialog(createClientForm.getFrame(), "Client Profile successfully created!\n" +
                        "Nationality:\t" + nat + "\n" +
                        "National ID:\t" + nationalId + "\n" +
                        "First Name:\t" + firstName + "\n" +
                        "Last Name:\t" + lastName );
            }
            else {
                JOptionPane.showMessageDialog(createClientForm.getFrame(), "A customer with the information " +
                        "you provided already exists. If you think this is an error, please contact your bank branch.");
            }
        }
        else {
            JOptionPane.showMessageDialog(createClientForm.getFrame(), "One of the user-created fields has an invalid data format");
        }
    }

    private void onLogout() {
        createClientForm.getFrame().dispose();
        new LoginFormController(service);
    }
}
