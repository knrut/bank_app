package com.rut.bank.model;

import com.rut.bank.repository.BankAccountRepository;
import com.rut.bank.repository.ClientRepository;
import com.rut.bank.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class Service {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;
    private BankAccount loggedInBankAccount;

    public Service(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository,
                   ClientRepository clientRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
    }

    public boolean registerUser(String login, String password, String nationalId) {
        if (bankAccountRepository.findByLogin(login).isPresent()) {
            return false;
        }

        bankAccountRepository.save(new BankAccount(login, password, nationalId));
        return true;
    }

    public boolean doesClientDataMatch(String nationalId, String firstName, String lastName) {
        if (clientRepository.findMatchingClient(nationalId, firstName, lastName).isPresent()) {
            return true;
        }
        return false;
    }

    public boolean loginUser(String login, String password) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findByLoginAndPassoword(login, password);
        if (bankAccount.isPresent()) {
            loggedInBankAccount = bankAccount.get();
            return true;
        }
        return false;
    }

    public BigDecimal makeDeposit(BigDecimal amount) {
        transactionRepository.save(new Transaction(TransactionType.DEPOSIT, loggedInBankAccount, amount, null));
        return loggedInBankAccount.makeDeposit(amount);
    }

    public BigDecimal makeWithdrawal(BigDecimal amount) {
        transactionRepository.save(new Transaction(TransactionType.WITHDRAWAL, loggedInBankAccount, amount, null));
        return loggedInBankAccount.makeWithdrawal(amount);
    }

    public BigDecimal getBalance() {
        return loggedInBankAccount.getBalance();
    }

    public BankAccount getLoggedInClient() {
        return loggedInBankAccount;
    }


    public BankAccountRepository getBankAccountRepository() {
        return bankAccountRepository;
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public void updateInfo() {
        bankAccountRepository.update(loggedInBankAccount);
    }

    public void makeTransfer(BigDecimal amount, String whereTo) {
        Optional<BankAccount> receiver = bankAccountRepository.findByLogin(whereTo);
        if (receiver.isPresent()) {
            transactionRepository.save(new Transaction(TransactionType.TRANSFER, loggedInBankAccount, amount, receiver.get()));
            loggedInBankAccount.makeWithdrawal(amount);
            receiver.get().makeDeposit(amount);
        }
    }

    public boolean createClientProfile(String nationalId, String firstName, String lastName, LocalDate dateOfBirth, Nationality nationality) {
        if (clientRepository.findByNationalId(nationalId).isPresent())
        {
            return false;
        }
        clientRepository.save(new Client(nationalId, firstName, lastName, dateOfBirth, nationality));
        return true;
    }

    public boolean doesBankAccountExist(String login) {
        return bankAccountRepository.findByLogin(login).isPresent();
    }

}
