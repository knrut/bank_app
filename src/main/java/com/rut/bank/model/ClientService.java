package com.rut.bank.model;

import com.rut.bank.repository.ClientRepository;

import java.time.LocalDate;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClientProfile(String pesel, String firstName, String lastName, LocalDate age, Nationality nationality) {
        clientRepository.save(new Client(pesel, firstName, lastName, age, nationality));
    }


}
