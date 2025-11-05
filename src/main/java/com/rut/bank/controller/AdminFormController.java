package com.rut.bank.controller;

import com.rut.bank.model.BankAccount;
import com.rut.bank.model.Client;
import com.rut.bank.model.Service;
import com.rut.bank.model.Transaction;
import com.rut.bank.repository.BankAccountRepository;
import com.rut.bank.repository.ClientRepository;
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
    private final ClientRepository clientRepository;

    private final AdminForm adminForm;
    private final GenericTableModel<BankAccount> accountsModel;
    private final GenericTableModel<Transaction> transactionsModel;
    private final GenericTableModel<Client> clientsModel;

    private ViewMode mode = ViewMode.ACCOUNTS;

    private enum ViewMode { ACCOUNTS, TRANSACTIONS, CLIENTS }

    public AdminFormController(
            Service service,
            BankAccountRepository bankAccountRepository,
            TransactionRepository transactionRepository,
            ClientRepository clientRepository
    ) {
        this.service = service;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;

        var dtFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var dFmt  = DateTimeFormatter.ISO_LOCAL_DATE;

        // --- Konta (dodana kolumna Client NationalId) ---
        accountsModel = new GenericTableModel<>(
                new String[]{"Account UUID","Client National ID", "Login","Balance","Date Created"},
                Arrays.<Function<BankAccount,Object>>asList(
                        BankAccount::getID,
                        BankAccount::getNationalId,
                        BankAccount::getLogin,
                        BankAccount::getBalance,
                        a -> a.getDateCreated().format(dtFmt)
                )
        );

        // --- Transakcje ---
        transactionsModel = new GenericTableModel<>(
                new String[]{"Transaction UUID", "Type","Performed By","Amount","Sent To","Executed At"},
                Arrays.<Function<Transaction,Object>>asList(
                        Transaction::getID,
                        Transaction::getTransactionType,
                        Transaction::getPerfomedByLogin,
                        Transaction::getAmount,
                        t -> t.getSentTo().map(BankAccount::getLogin).orElse("-"),
                        t -> t.getExecutedAt().format(dtFmt)
                )
        );

        // --- Klienci (pełne atrybuty klasy Client) ---
        clientsModel = new GenericTableModel<>(
                new String[]{"Client UUID","Client National ID","First Name","Last Name","Date of Birth","Nationality"},
                Arrays.<Function<Client,Object>>asList(
                        Client::getID,
                        Client::getNationalId,
                        Client::getFirstName,
                        Client::getLastName,
                        c -> c.getDateOfBirth().format(dFmt),
                        Client::getNationality
                )
        );

        adminForm = new AdminForm(this::onLogout);

        // domyślnie pokazujemy konta
        adminForm.setTableModel(accountsModel);
        reloadAccounts();

        // przełączniki widoków
        adminForm.onShowAccount(this::showAccounts);
        adminForm.onShowTransactions(this::showTransactions);
        adminForm.onShowClients(this::showClients); // ⬅️ DODAJ ten callback w AdminForm
        adminForm.onRefresh(this::reloadCurrentTable);
    }

    // --- Handlery zakładek ---
    private void showAccounts() {
        mode = ViewMode.ACCOUNTS;
        adminForm.setTableModel(accountsModel);
        reloadAccounts();
    }

    private void showTransactions() {
        mode = ViewMode.TRANSACTIONS;
        adminForm.setTableModel(transactionsModel);
        reloadTransactions();
    }

    private void showClients() {
        mode = ViewMode.CLIENTS;
        adminForm.setTableModel(clientsModel);
        reloadClients();
    }

    private void reloadCurrentTable() {
        switch (mode) {
            case ACCOUNTS -> reloadAccounts();
            case TRANSACTIONS -> reloadTransactions();
            case CLIENTS -> reloadClients();
        }
    }

    // --- Re-loaders ---
    private void reloadTransactions() {
        transactionsModel.reload(transactionRepository.findALL());
    }

    private void reloadAccounts() {
        accountsModel.reload(bankAccountRepository.findALL());
    }

    private void reloadClients() {
        clientsModel.reload(clientRepository.findALL());
    }

    private void onLogout() {
        JOptionPane.showMessageDialog(adminForm.getFrame(), "Logged out");
        adminForm.getFrame().dispose();
        new LoginFormController(service);
    }
}
