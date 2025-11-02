package com.rut.bank.model;

import java.util.UUID;

public class Client extends Entity<UUID> {
    private String firstName;
    private String lastName;
    private int age;
    private BankAccount assignedBankAccount;

    public Client(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public BankAccount getAssignedBankAccount() {
        return assignedBankAccount;
    }

    public void setAssignedBankAccount(BankAccount assignedBankAccount) {
        this.assignedBankAccount = assignedBankAccount;
    }
}

