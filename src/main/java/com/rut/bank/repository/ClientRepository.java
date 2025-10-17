package com.rut.bank.repository;

import com.rut.bank.model.Client;

import java.util.Optional;
import java.util.UUID;

public class ClientRepository extends InFileRepository<UUID, Client> {

    public ClientRepository(String filePath) {
        super(filePath);
    }

    public Optional<Client> findByLogin(String login) {
        return findALL()
                .stream()
                .filter(c -> c.getLogin().equalsIgnoreCase(login)).
                findFirst();
    }

    public Optional<Client> findByLoginAndPassoword(String login, String password) {
        return findALL()
                .stream()
                .filter(c -> c.getLogin().equalsIgnoreCase(login) && c.getPassword().equals(password))
                .findFirst();
    }

}
