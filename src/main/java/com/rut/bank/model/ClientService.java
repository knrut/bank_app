package com.rut.bank.model;

import com.rut.bank.repository.ClientRepository;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClientProfile(String firstName, String lastName, int age, String nationality) {
        clientRepository.save(new Client(firstName, lastName, age, nationality));
    }


}
