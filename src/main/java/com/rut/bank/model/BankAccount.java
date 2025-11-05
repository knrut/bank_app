package com.rut.bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BankAccount extends Entity<UUID> implements Serializable {
    private String login;
    private String password;
    private BigDecimal balance;
    private LocalDateTime dataCreated;
    private Role role;
    private String nationalId;

    public BankAccount(String login, String password, String nationalId) {
        this(login, password, BigDecimal.ZERO, Role.USER, nationalId);
    }

    public BankAccount(String login, String password, BigDecimal balance, Role role, String nationalId) {
        setID(UUID.randomUUID());
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.dataCreated = LocalDateTime.now();
        this.role = role;
        this.nationalId = nationalId;
    }

    public BankAccount(String login, String password, BigDecimal zero) {
        super();
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public BigDecimal getBalance() { return balance; }
    public LocalDateTime getDateCreated() { return  dataCreated; }
    public Role getRole() { return role; }
    public Object getNationalId() { return nationalId; }


    public BigDecimal makeDeposit(BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }

    public BigDecimal makeWithdrawal(BigDecimal amount) {
        balance = balance.subtract(amount);
        return balance;
    }

}
