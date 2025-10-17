package com.rut.bank;

import com.rut.bank.controller.LoginFormController;
import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.Client;
import com.rut.bank.model.Role;
import com.rut.bank.view.console.BankAccountView;
import com.rut.bank.repository.ClientRepository;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ClientRepository repo = new ClientRepository("/Users/kn/IdeaProjects/bank-app/src/main/java/com/rut/bank/repo.txt");
        repo.save(new Client("admin", "admin", BigDecimal.ZERO, Role.ADMIN));
        BankAccountService service = new BankAccountService(repo);
        LoginFormController controller = new LoginFormController(service);
    }
}
