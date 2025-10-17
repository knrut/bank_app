package com.rut.bank.controller;

import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.Client;
import com.rut.bank.model.Entity;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.UserForm;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.UUID;

public class UserFormController {
    private final BankAccountService service;
    private final UserForm userForm;
    
    public UserFormController(BankAccountService service) {
        this.service = service;
        this.userForm = new UserForm(this::onLogout);
        updateBalance();
        control();
    }

    private void control() {
        userForm.getButtonDeposit().addActionListener(e -> onDeposit());
        userForm.getButtonWithdraw().addActionListener(e -> onWithdraw());
        userForm.getButtonInfo().addActionListener(e -> onInfo());
        userForm.getButtonLogout().addActionListener(e -> onLogout());
    }

    private void onDeposit() {
        String input = JOptionPane.showInputDialog(userForm.getFrame(), "Enter deposit amount:");
        if (DataValidator.isDecimal(input)) {
            BigDecimal amount = new BigDecimal(input);
            if (DataValidator.validateDeposit(amount)) {
                service.makeDeposit(amount);
                updateBalance();
                service.updateInfo();
            } else {
                JOptionPane.showMessageDialog(userForm.getFrame(), "Incorrect amount");
            }
        }
    }

    private void onWithdraw() {
        String input = JOptionPane.showInputDialog(userForm.getFrame(), "Enter withdrawal amount:");
        if (DataValidator.isDecimal(input)) {
            BigDecimal amount = new BigDecimal(input);
            if (DataValidator.validateWithdrawal(amount, service.getBalance())) {
                service.makeWithdrawal(amount);
                updateBalance();
                service.updateInfo();
            } else {
                JOptionPane.showMessageDialog(userForm.getFrame(), "Exceeded withdrawal funds");
            }
        }
    }

    private void onLogout() {
        JOptionPane.showMessageDialog(userForm.getFrame(), "Logged out");
        userForm.getFrame().dispose();
        new LoginFormController(service);
    }

    private void onInfo() {
        Client client = service.getLoggedInClient();
        JOptionPane.showMessageDialog(userForm.getFrame(), "User: " + client.getLogin() + "\n- Account created at: "
                + client.getDateCreated());
    }

    private void updateBalance() {
        userForm.getLabelBalance().setText("Balance: " + service.getBalance() + "$");
    }
}
