package com.rut.bank.model;

import com.rut.bank.repository.ClientRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class BankAccountService {
    private final ClientRepository clientRepository;
    private Client loggedInClient;

    public BankAccountService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean registerUser(String login, String password) {
        if (clientRepository.findByLogin(login).isPresent()) {
            return false;
        }

        clientRepository.save(new Client(login, password));
        return true;
    }

    public boolean loginUser(String login, String password) {
        Optional<Client> client = clientRepository.findByLoginAndPassoword(login, password);
        if (client.isPresent()) {
            loggedInClient = client.get();
            return true;
        }
        return false;
    }

    public BigDecimal makeDeposit(BigDecimal amount) {
        return loggedInClient.makeDeposit(amount);
    }

    public BigDecimal makeWithdrawal(BigDecimal amount) {
        return loggedInClient.makeWithdrawal(amount);
    }

    public BigDecimal getBalance() {
        return loggedInClient.getBalance();
    }

    public Client getLoggedInClient() {
        return loggedInClient;
    }
}
