package com.rut.bank.model;

import java.math.BigDecimal;
import java.util.Optional;

public class BankAccountService {
    private final ClientRepository clientRepository;
    private Client loggedInClient;

    public BankAccountService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean registerUser(String login, String password) {
        boolean taken = clientRepository
                .findALL()
                .stream()
                .allMatch(c -> c.getLogin().equalsIgnoreCase(login));
        if (taken) return false;

        clientRepository.save(new Client(login, password));
        return true;
    }

    public boolean loginUser(String login, String password) {
        Optional<Client> match = clientRepository
                .findALL()
                .stream()
                .filter(c -> c.getLogin().equalsIgnoreCase(login) && c.getPassword().equals(password)).findFirst();

        if (match.isPresent()) {
            loggedInClient = match.get();
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
