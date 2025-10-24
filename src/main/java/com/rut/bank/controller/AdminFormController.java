package com.rut.bank.controller;

import com.rut.bank.model.BankAccount;
import com.rut.bank.repository.BankAccountRepository;
import com.rut.bank.table.GenericTableModel;
import com.rut.bank.view.AdminForm;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Function;

public class AdminFormController {
    private final BankAccountRepository repo;
    private final AdminForm view;
    private final GenericTableModel<BankAccount> model;

    public AdminFormController(BankAccountRepository repo) {
        this.repo = repo;

        var fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        model = new GenericTableModel<BankAccount>(
                new String[]{"UUID","Login","Balance","Date Created"},
                Arrays.<Function<BankAccount,Object>>asList(
                        BankAccount::getID,
                        BankAccount::getLogin,
                        BankAccount::getBalance,
                        c -> c.getDateCreated().format(fmt)
                )
        );

        view = new AdminForm(this::onLogout);
        view.setTableModel(model);

        view.setTableModel(model);
        model.reload(repo.findALL());
    }

    private void reload() {
        model.reload(repo.findALL());   // Controller pobiera dane i ładuje do modelu
    }

    private void onLogout() {
        view.getFrame().dispose();
        // … np. powrót do LoginFormController
        // new LoginFormController(sharedService);
    }
}
