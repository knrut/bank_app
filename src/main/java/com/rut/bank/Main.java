package com.rut.bank;
import com.rut.bank.controller.LoginFormController;
import com.rut.bank.model.*;
import com.rut.bank.repository.BankAccountRepository;
import com.rut.bank.repository.ClientRepository;
import com.rut.bank.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Client adminClient = new Client(
                "0000",
                "Admin",
                "Admin",
                LocalDate.of(1990, 5, 12),
                Nationality.POLAND);
        BankAccount admin = new BankAccount(
                "admin",
                "admin",
                BigDecimal.valueOf(1000.00),
                Role.ADMIN,
                "0000");

        BankAccountRepository bankAccountRepository = new BankAccountRepository("/Users/kn/IdeaProjects/bank-app/src/main/java/com/rut/bank/bankaccounRe.txt");
        TransactionRepository transactionRepository = new TransactionRepository("/Users/kn/IdeaProjects/bank-app/src/main/java/com/rut/bank/transactionRe.txt");
        ClientRepository clientRepository = new ClientRepository("/Users/kn/IdeaProjects/bank-app/src/main/java/com/rut/bank/clientRe.txt");
        clientRepository.save(adminClient);
        bankAccountRepository.save(admin);
        Service service = new Service(bankAccountRepository, transactionRepository, clientRepository);
        service.setRememberMe(false);
        LoginFormController controller = new LoginFormController(service);
    }
}
