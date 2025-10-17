package com.rut.bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Client extends Entity<UUID> implements Serializable {
    private String login;
    private String password;
    private BigDecimal balance;
    private LocalDateTime dataCreated;
    private Role role;

    public Client(String login, String password) {
        this(login, password, BigDecimal.ZERO, Role.USER);
    }

    public Client(String login, String password, BigDecimal balance, Role role) {
        setID(UUID.randomUUID());
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.dataCreated = LocalDateTime.now();
        this.role = role;
    }

    public Client(String login, String password, BigDecimal zero) {
        super();
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public BigDecimal getBalance() { return balance; }
    public LocalDateTime getDateCreated() { return  dataCreated; }
    public Role getRole() { return role; }


    public BigDecimal makeDeposit(BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }

    public BigDecimal makeWithdrawal(BigDecimal amount) {
        balance = balance.subtract(amount);
        return balance;
    }

}
