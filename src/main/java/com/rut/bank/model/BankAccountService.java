package com.rut.bank.model;

import com.rut.bank.repository.BankAccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private BankAccount loggedInBankAccount;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public boolean registerUser(String login, String password) {
        if (bankAccountRepository.findByLogin(login).isPresent()) {
            return false;
        }

        bankAccountRepository.save(new BankAccount(login, password));
        return true;
    }

    public boolean loginUser(String login, String password) {
        Optional<BankAccount> client = bankAccountRepository.findByLoginAndPassoword(login, password);
        if (client.isPresent()) {
            loggedInBankAccount = client.get();
            return true;
        }
        return false;
    }

    public BigDecimal makeDeposit(BigDecimal amount) {
        return loggedInBankAccount.makeDeposit(amount);
    }

    public BigDecimal makeWithdrawal(BigDecimal amount) {
        return loggedInBankAccount.makeWithdrawal(amount);
    }

    public BigDecimal getBalance() {
        return loggedInBankAccount.getBalance();
    }

    public BankAccount getLoggedInClient() {
        return loggedInBankAccount;
    }

    public BankAccountRepository getClientRepository() {
        return bankAccountRepository;
    }

    public void updateInfo() {
        bankAccountRepository.update(loggedInBankAccount);
    }

    public void makeTransfer(BigDecimal amount, String whereTo) {
        Optional<BankAccount> receiver = bankAccountRepository.findByLogin(whereTo);
        if (receiver.isPresent()) {
            loggedInBankAccount.makeWithdrawal(amount);
            receiver.get().makeDeposit(amount);
        }
    }
}
