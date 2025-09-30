package com.rut.bank;

import com.rut.bank.model.BankAccountController;
import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.BankAccountView;
import com.rut.bank.model.ClientRepository;

public class Main {
    public static void main(String[] args) {
        ClientRepository repo = new ClientRepository();
        BankAccountService service = new BankAccountService(repo);
        BankAccountView view = new BankAccountView();
        BankAccountController controller = new BankAccountController(service, view);

        controller.start();
    }
}