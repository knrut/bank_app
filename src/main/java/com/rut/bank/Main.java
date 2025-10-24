package com.rut.bank;

import com.rut.bank.controller.LoginFormController;
import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.BankAccount;
import com.rut.bank.model.Role;
import com.rut.bank.repository.BankAccountRepository;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BankAccountRepository repo = new BankAccountRepository("/Users/kn/IdeaProjects/bank-app/src/main/java/com/rut/bank/repo.txt");
        BankAccountService service = new BankAccountService(repo);
        LoginFormController controller = new LoginFormController(service);
    }
}
