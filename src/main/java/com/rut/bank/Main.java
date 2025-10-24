package com.rut.bank;

import com.rut.bank.controller.LoginFormController;
import com.rut.bank.model.BankAccountService;
import com.rut.bank.model.BankAccount;
import com.rut.bank.model.Client;
import com.rut.bank.model.Role;
import com.rut.bank.repository.BankAccountRepository;
import com.rut.bank.repository.TransactionRepository;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        //BankAccount admin = new BankAccount("admin", "admin", new BigDecimal(0), Role.ADMIN);
        BankAccountRepository bankAccountRepository = new BankAccountRepository("/Users/kn/IdeaProjects/bank-app/src/main/java/com/rut/bank/bankaccountRepo.txt");
        com.rut.bank.repository.TransactionRepository transactionRepository = new TransactionRepository("/Users/kn/IdeaProjects/bank-app/src/main/java/com/rut/bank/transactionRepo.txt");
       // bankAccountRepository.save(admin);
        BankAccountService service = new BankAccountService(bankAccountRepository, transactionRepository);
        LoginFormController controller = new LoginFormController(service);
    }
}
