package com.rut.bank.controller;

import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.BankAccount;
import com.rut.bank.util.DataValidator;
import com.rut.bank.view.BankAccountForm;

import javax.swing.*;
import java.math.BigDecimal;

public class BankAccountController {
    private final BankAccountService service;
    private final BankAccountForm bankAccountForm;
    
    public BankAccountController(BankAccountService service) {
        this.service = service;
        this.bankAccountForm = new BankAccountForm(this::onLogout);
        updateBalance();
        control();
    }

    private void control() {
        bankAccountForm.getButtonDeposit().addActionListener(e -> onDeposit());
        bankAccountForm.getButtonWithdraw().addActionListener(e -> onWithdraw());
        bankAccountForm.getButtonInfo().addActionListener(e -> onInfo());
        bankAccountForm.getButtonLogout().addActionListener(e -> onLogout());
        bankAccountForm.getButtonTransfer().addActionListener(e -> onTransfer());
    }

    private void onTransfer() {
        String input_amount = JOptionPane.showInputDialog(bankAccountForm.getFrame(), "Enter transfer amount: ");
        String input_whereto = JOptionPane.showInputDialog(bankAccountForm.getFrame(), "Enter whereTo: ");
        if (DataValidator.isDecimal(input_amount)) {
            BigDecimal amount = new BigDecimal(input_amount);
            if (DataValidator.validateWithdrawal(amount, service.getBalance())) {
                service.makeTransfer(amount, input_whereto);
                updateBalance();
                service.updateInfo();
            } else {
                JOptionPane.showMessageDialog(bankAccountForm.getFrame(), "Incorrect amount");
            }
        }
    }

    private void onDeposit() {
        String input = JOptionPane.showInputDialog(bankAccountForm.getFrame(), "Enter deposit amount:");
        if (DataValidator.isDecimal(input)) {
            BigDecimal amount = new BigDecimal(input);
            if (DataValidator.validateDeposit(amount)) {
                service.makeDeposit(amount);
                updateBalance();
                service.updateInfo();
            } else {
                JOptionPane.showMessageDialog(bankAccountForm.getFrame(), "Incorrect amount");
            }
        }
    }

    private void onWithdraw() {
        String input = JOptionPane.showInputDialog(bankAccountForm.getFrame(), "Enter withdrawal amount:");
        if (DataValidator.isDecimal(input)) {
            BigDecimal amount = new BigDecimal(input);
            if (DataValidator.validateWithdrawal(amount, service.getBalance())) {
                service.makeWithdrawal(amount);
                updateBalance();
                service.updateInfo();
            } else {
                JOptionPane.showMessageDialog(bankAccountForm.getFrame(), "Exceeded withdrawal funds");
            }
        }
    }

    private void onLogout() {
        JOptionPane.showMessageDialog(bankAccountForm.getFrame(), "Logged out");
        bankAccountForm.getFrame().dispose();
        new LoginFormController(service);
    }

    private void onInfo() {
        BankAccount bankAccount = service.getLoggedInClient();
        JOptionPane.showMessageDialog(bankAccountForm.getFrame(), "User: " + bankAccount.getLogin() + "\n- Account created at: "
                + bankAccount.getDateCreated());
    }

    private void updateBalance() {
        bankAccountForm.getLabelBalance().setText("Balance: " + service.getBalance() + "$");
    }
}
