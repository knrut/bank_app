package com.rut.bank.controller;

import com.rut.bank.model.BankAccount;
import com.rut.bank.model.Service;
import com.rut.bank.model.Transaction;
import com.rut.bank.repository.BankAccountRepository;
import com.rut.bank.repository.TransactionRepository;
import com.rut.bank.table.GenericTableModel;
import com.rut.bank.view.AdminForm;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Function;

public class AdminFormController {
    private final Service service;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final AdminForm adminForm;
    private final GenericTableModel<BankAccount> accountsModel;
    private final GenericTableModel<Transaction> transactionsModel;

    private boolean showingAccounts = true;

    public AdminFormController(Service service, BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.service = service;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;

        var fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        accountsModel = new GenericTableModel<BankAccount>(
                new String[]{"UUID","Login","Balance","Date Created"},
                Arrays.<Function<BankAccount,Object>>asList(
                        BankAccount::getID,
                        BankAccount::getLogin,
                        BankAccount::getBalance,
                        c -> c.getDateCreated().format(fmt)
                )
        );

        transactionsModel = new GenericTableModel<>(
                new String[]{"Type","Performed By","Amount","Sent To","Executed At"},
                Arrays.<Function<Transaction,Object>>asList(
                        Transaction::getTransactionType,
                        Transaction::getPerfomedByLogin,
                        Transaction::getAmount,
                        t -> t.getSentTo().map(BankAccount::getLogin).orElse("-"),
                        t -> t.getExecutedAt().format(fmt)
                )
        );

        adminForm = new AdminForm(this::onLogout);
        adminForm.setTableModel(accountsModel);
        reloadAccounts();

        adminForm.onShowAccount(this::showAccounts);
        adminForm.onShowTransactions(this::showTransactions);
        adminForm.onRefresh(this::reloadCurrentTable);
    }

    private void showAccounts() {
        showingAccounts = true;
        adminForm.setTableModel(accountsModel);
        reloadAccounts();
    }

    private void showTransactions() {
        showingAccounts = false;
        adminForm.setTableModel(transactionsModel);
        reloadTransactions();
    }

    private void reloadCurrentTable() {
        if (showingAccounts) reloadAccounts();
        else reloadTransactions();
    }

    private void reloadTransactions() {
        transactionsModel.reload(transactionRepository.findALL());
    }

    private void reloadAccounts() {
        accountsModel.reload(bankAccountRepository.findALL());
    }

    private void onLogout() {
        JOptionPane.showMessageDialog(adminForm.getFrame(), "Logged out");
        adminForm.getFrame().dispose();
        new LoginFormController(service);
    }
}
