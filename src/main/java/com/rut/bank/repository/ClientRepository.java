package com.rut.bank.repository;

import com.rut.bank.model.Client;
import com.rut.bank.model.Nationality;

import java.util.Optional;
import java.util.UUID;

public class ClientRepository extends InFileRepository<UUID, Client> {

    public ClientRepository(String filePath) {
        super(filePath);
    }

    public Optional<Client> findByNationalId(String nationalId) {
        return findALL()
                .stream()
                .filter(c -> c.getNationalId().equalsIgnoreCase(nationalId))
                .findFirst();
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

    public Optional<Client> findByNationality(Nationality nationality) {
        return findALL().stream().filter(c -> c.getNationality() == nationality).findFirst();
    }

    public Optional<Client> findMatchingClient(String nationalId, String firstName, String lastName) {
        return findALL().stream().filter(client -> client.getNationalId().equalsIgnoreCase(nationalId)
                && client.getFirstName().equalsIgnoreCase(firstName) &&
                client.getLastName().equalsIgnoreCase(lastName)).findFirst();
    }

}
