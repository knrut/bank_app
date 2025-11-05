package com.rut.bank.controller;

import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.Nationality;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.ClientForm;

import javax.swing.*;
import java.time.LocalDate;

public class ClientFormController {
    private final BankAccountService service;
    private final ClientForm clientForm;

    public ClientFormController(BankAccountService service) {
        this.service = service;
        this.clientForm = new ClientForm(this::onLogout);
        control();
    }

    private void control() {
        clientForm.getCreateButton().addActionListener(e -> onCreateClient());
        clientForm.getBackButton().addActionListener(e -> onLogout());
    }

    private void onCreateClient() {
        String nationalId = clientForm.getNationalId();
        String firstName = clientForm.getFirstNameField().getText();
        String lastName = clientForm.getLastNameField().getText();
        LocalDate dob = clientForm.getDateOfBirth();
        Nationality nat = clientForm.getSelectedNationality();
        if (DataValidator.validateNationalId(nationalId) && DataValidator.validateFirstName(firstName)
                && DataValidator.validateLastName(lastName) && DataValidator.validateDithOfBirth(dob)) {
            if (service.createClientProfile(nationalId, firstName, lastName, dob, nat)) {
                JOptionPane.showMessageDialog(clientForm.getFrame(), "Client Profile successfully created!\n" +
                        "Nationality:\t" + nat + "\n" +
                        "National ID:\t" + nationalId + "\n" +
                        "First Name:\t" + firstName + "\n" +
                        "Last Name:\t" + lastName );
            }
            else {
                JOptionPane.showMessageDialog(clientForm.getFrame(), "A customer with the information " +
                        "you provided already exists. If you think this is an error, please contact your bank branch.");
            }
        }
        else {
            JOptionPane.showMessageDialog(clientForm.getFrame(), "One of the user-created fields has an invalid data format");
        }
    }

    private void onLogout() {
        clientForm.getFrame().dispose();
        new LoginFormController(service);
    }
}
