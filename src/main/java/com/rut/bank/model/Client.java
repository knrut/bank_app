package com.rut.bank.model;

import java.time.LocalDate;
import java.util.UUID;

public class Client extends Entity<UUID> {
    private String nationalId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Nationality nationality;

    public Client(String natioanlId, String firstName, String lastName, LocalDate dateOfBirth, Nationality nationality) {
        setID(UUID.randomUUID());
        this.nationalId = natioanlId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Nationality getNationality() {
        return nationality;
    }

}

