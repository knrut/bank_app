package com.rut.bank.model;

import java.util.UUID;

public class Client extends Entity<UUID> {
    private String firstName;
    private String lastName;
    private int dateOfBirth;
    private String nationality;

    public Client(String firstName, String lastName, int dateOfBirth, String nationality) {
        setID(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

}

