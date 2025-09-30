package com.rut.bank.controller;

import com.rut.bank.model.BankAccountService;
import com.rut.bank.view.BankAccountView;
import com.rut.bank.util.DataValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankAccountController {
    private final BankAccountService service;
    private final BankAccountView view;

    public BankAccountController(BankAccountService service, BankAccountView view) {
        this.service = service;
        this.view = view;
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.printMessage("\n--- MAIN MENU ---");
            view.printMessage("1. Register new account");
            view.printMessage("2. Login into account");
            view.printMessage("q - quit");

            String choice = view.askForInput("Select operation: ");

            switch (choice) {
                case "q" -> running = false;
                case "1" -> handleRegistration();
                case "2" -> handleLogin();
            }
        }
    }

    private void handleRegistration() {
        String login;
        while (true) {
            login = view.askForInput("Enter login (3-20 characters, letters/numbers): ");
            if (login.equals("q")) return;
            else if (DataValidator.validateLogin(login)) break;
            else view.printMessage("Incorrect login. Please try again.");
        }

        String password;
        while (true) {
            password = view.askForInput("Enter your password (min. 6 characters): ");
            if (password.equals("q")) return;
            else if (DataValidator.validatePassword(password)) break;
            else view.printMessage("Incorrect password. Please try again.");
        }

        if (service.registerUser(login, password)) {
            view.printMessage("User: " + login + " has been successfully registered!");
        } else view.printMessage("Login: " + login + " is already taken");
    }

    private void handleLogin() {
        String login = view.askForInput("Enter your login: ");
        if (login.equals("q")) return;

        String password = view.askForInput("Enter your password: ");
        if (password.equals("q")) return;

        if (service.loginUser(login, password)) {
            view.printMessage("You've been successfully logged in as: " + login);
            loggedInMenu();
        } else {
            view.printMessage("Incorrect login or password");
        }
    }


    private void loggedInMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            view.printMessage("\n--- USER MENU ---");
            view.printMessage("1. Make Deposit");
            view.printMessage("2. Withdraw Deposit");
            view.printMessage("3. Check Balance");
            view.printMessage("4. Account Information");
            view.printMessage("q - Log out");

            String choice = view.askForInput("Select operation: ");

            switch (choice) {
                case "1" -> {
                    while (true) {
                        String input = view.askForInput("Enter the amount to deposit (or q to exit): ");
                        if (input.equals("q")) break;
                        BigDecimal amount = view.parseStringToBigDecimal(input);
                        if (DataValidator.validateDeposit(amount)) {
                            BigDecimal balance = service.makeDeposit(amount);
                            view.printMessage("Funds deposited. Balance: " + balance);
                            break;
                        } else {
                            view.printMessage("Invalid amount. It must be greater than 0 and less than 100000.");
                        }
                    }
                }
                case "2" -> {
                    while (true) {
                        String input = view.askForInput("Enter the amount to withdraw (or q to exit): ");
                        if (input.equals("q")) break;
                        BigDecimal amount = view.parseStringToBigDecimal(input);
                        if (amount == null) {
                            view.printMessage("Invalid number, try again.");
                            continue;
                        }
                        if (DataValidator.validateWithdrawal(amount, service.getBalance())) {
                            service.makeWithdrawal(amount);
                            view.printMessage("Withdrawal successful. Balance: " + service.getBalance());
                            break;
                        } else {
                            view.printMessage("Invalid amount or insufficient balance.");
                        }
                    }
                }
                case "3" -> view.printMessage("Your balance: " + service.getBalance());
                case "4" -> {
                    LocalDateTime info = service.getLoggedInClient().getDateCreated();
                    view.printMessage("This account was created: " + info);
                }
                case "q" -> loggedIn = false;
                default -> view.printMessage("Invalid option");
            }
        }
    }
}




