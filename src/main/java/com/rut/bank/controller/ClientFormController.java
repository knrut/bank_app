package com.rut.bank.controller;

import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.Nationality;
import com.rut.bank.view.ClientForm;

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
    }

    private void onLogout() {
        clientForm.getFrame().dispose();
        new LoginFormController(service);
    }
}
