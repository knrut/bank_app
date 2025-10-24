package com.rut.bank.controller;

import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.BankAccount;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.UserForm;

import javax.swing.*;
import java.math.BigDecimal;

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
        userForm.getButtonTransfer().addActionListener(e -> onTransfer());
    }

    private void onTransfer() {
        String input_amount = JOptionPane.showInputDialog(userForm.getFrame(), "Enter transfer amount: ");
        String input_whereto = JOptionPane.showInputDialog(userForm.getFrame(), "Enter whereTo: ");
        if (DataValidator.isDecimal(input_amount)) {
            BigDecimal amount = new BigDecimal(input_amount);
            if (DataValidator.validateWithdrawal(amount, service.getBalance())) {
                service.makeTransfer(amount, input_whereto);
                updateBalance();
                service.updateInfo();
            } else {
                JOptionPane.showMessageDialog(userForm.getFrame(), "Incorrect amount");
            }
        }
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
        BankAccount bankAccount = service.getLoggedInClient();
        JOptionPane.showMessageDialog(userForm.getFrame(), "User: " + bankAccount.getLogin() + "\n- Account created at: "
                + bankAccount.getDateCreated());
    }

    private void updateBalance() {
        userForm.getLabelBalance().setText("Balance: " + service.getBalance() + "$");
    }
}
