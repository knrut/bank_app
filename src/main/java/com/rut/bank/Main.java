package com.rut.bank;

import com.rut.bank.controller.BankAccountController;
import com.rut.bank.controller.LoginFormController;
import com.rut.bank.model.BankAccountService;
import com.rut.bank.view.console.BankAccountView;
import com.rut.bank.repository.ClientRepository;

public class Main {
    public static void main(String[] args) {
        ClientRepository repo = new ClientRepository();
        BankAccountService service = new BankAccountService(repo);
        LoginFormController controller = new LoginFormController(service);

    }
}