package com.rut.bank.repository;

import com.rut.bank.model.BankAccount;

import java.util.Optional;
import java.util.UUID;

public class BankAccountRepository extends InFileRepository<UUID, BankAccount> {

    public BankAccountRepository(String filePath) {
        super(filePath);
    }

    public Optional<BankAccount> findByLogin(String login) {
        return findALL()
                .stream()
                .filter(c -> c.getLogin().equalsIgnoreCase(login)).
                findFirst();
    }

    public Optional<BankAccount> findByLoginAndPassoword(String login, String password) {
        return findALL()
                .stream()
                .filter(c -> c.getLogin().equalsIgnoreCase(login) && c.getPassword().equals(password))
                .findFirst();
    }

}
