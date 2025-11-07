package com.rut.bank.controller;

import com.rut.bank.model.Service;
import com.rut.bank.model.BankAccount;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.BankAccountForm;

import javax.swing.*;
import java.math.BigDecimal;

public class BankAccountController {
    private final Service service;
    private final BankAccountForm bankAccountForm;
    
    public BankAccountController(Service service) {
        this.service = service;
        this.bankAccountForm = new BankAccountForm(this::onLogout);
        updateBalance();
        control();
    }

    private void control() {
        bankAccountForm.getButtonDeposit().addActionListener(e -> onDeposit());
        bankAccountForm.getButtonWithdraw().addActionListener(e -> onWithdraw());
        bankAccountForm.getButtonTransfer().addActionListener(e -> onTransfer());
        bankAccountForm.getButtonInfo().addActionListener(e -> onInfo());
        bankAccountForm.getButtonLogout().addActionListener(e -> onLogout());
    }

    private void onDeposit() {
        String input = getInput("Enter deposit amount:");

        if (!DataValidator.isDecimal(input)) {
            showMessage("Please enter a valid amount");
            return;
        }

        BigDecimal amount = new BigDecimal(input.trim());

        if (!DataValidator.validateDeposit(amount)) {
            showMessage("Incorrect amount (must be positive)");
            return;
        }

        service.makeDeposit(amount);
        service.updateInfo();
        updateBalance();
        showMessage("Deposit successful: " + amount);
    }

    private void onWithdraw() {
        String input = getInput("Enter withdrawal amount:");

        if (!DataValidator.isDecimal(input)) {
            showMessage("Please enter a valid amount");
            return;
        }

        BigDecimal amount = new BigDecimal(input.trim());

        if (!DataValidator.validateWithdrawal(amount, service.getBalance())) {
            showMessage("Exceeded withdrawal funds");
            return;
        }

        service.makeWithdrawal(amount);
        service.updateInfo();
        updateBalance();

    }

    private void onTransfer() {
        String input_whereto = getInput("Enter the recipient: ");
        if (!service.doesBankAccountExist(input_whereto)) {
            showMessage("The recipient does not exist");
            return;
        }

        String input_amount = getInput("Enter transfer amount: ");
        if (!DataValidator.isDecimal(input_amount)) {
            showMessage("Please enter a valid amount");
            return;
        }

        BigDecimal amount = new BigDecimal(input_amount.trim());

        if (!DataValidator.validateWithdrawal(amount, service.getBalance())) {
            showMessage("Exceeded transfer funds");
            return;
        }

        service.makeTransfer(amount, input_whereto);
        service.updateInfo();
        updateBalance();

    }

    private void updateBalance() {
        bankAccountForm.getLabelBalance().setText("Balance: " + service.getBalance() + "$");
    }

    private void onInfo() {
        BankAccount bankAccount = service.getLoggedInClient();
        showMessage("User: " + bankAccount.getLogin() + "\n- Account created at: "
                + bankAccount.getDateCreated());
    }

    private void onLogout() {
        showMessage("Logged out");
        disposeWindow();
        new LoginFormController(service);
    }

    private String getInput(String message) {
        return JOptionPane.showInputDialog(bankAccountForm.getFrame(), message);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(bankAccountForm.getFrame(), message);
    }

    private void disposeWindow() {
        bankAccountForm.getFrame().dispose();
    }

}
