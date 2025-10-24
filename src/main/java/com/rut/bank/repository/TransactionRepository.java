package com.rut.bank.repository;

import com.rut.bank.model.BankAccount;
import com.rut.bank.model.Transaction;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class TransactionRepository extends InFileRepository<UUID, Transaction> {
    public TransactionRepository(String filePath) {
        super(filePath);
    }

    public Optional<Transaction> findByBankAccount(String login) {
        return findALL().stream().filter(c -> c.getPerfomedByLogin().equalsIgnoreCase(login)).findFirst();
    }

    public Optional<Transaction> findByAmount(BigDecimal amount) {
        return findALL().stream().filter(c -> c.getAmount().compareTo(amount) >= 0).findFirst();
    }



}
