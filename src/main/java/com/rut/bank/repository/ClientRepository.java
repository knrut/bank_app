package com.rut.bank.repository;

import com.rut.bank.model.Client;

import java.util.Optional;
import java.util.UUID;

public class ClientRepository extends InFileRepository<UUID, Client> {

    public ClientRepository(String filePath) {
        super(filePath);
    }

    public Optional<Client> findByFirstName(String firstName) {
        return findALL()
                .stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName))
                .findFirst();
    }

    public Optional<Client> findByLastName(String lastName) {
        return findALL()
                .stream()
                .filter(c -> c.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }


}
