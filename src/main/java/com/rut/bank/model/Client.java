package com.rut.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Client extends Entity<UUID> {
    private String login;
    private String password;
    private BigDecimal balance;
    private LocalDateTime dataCreated;

    public Client(String login, String password) {
        this(login, password, BigDecimal.ZERO);
    }

    public Client(String login, String password, BigDecimal balance) {
        setID(UUID.randomUUID());
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.dataCreated = LocalDateTime.now();
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public BigDecimal getBalance() { return balance; }
    public LocalDateTime getDataCreated() { return  dataCreated; }


    public BigDecimal makeDeposit(BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }

    public BigDecimal makeWithdrawal(BigDecimal amount) {
        balance = balance.subtract(amount);
        return balance;
    }

}
