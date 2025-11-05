package com.rut.bank.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataValidator {
    public static boolean validateLogin(String login) { return login.matches("[a-zA-Z0-9._]{3,20}"); }

    public static boolean validatePassword(String password) { return password.length() >= 6 && password.length() <= 50; }

    public static boolean validateDeposit(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(new BigDecimal("100000")) < 0;
    }

    public static boolean validateWithdrawal(BigDecimal amount, BigDecimal balance) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0;
    }

    public static boolean isDecimal(String input) {
        if (input == null || input.isBlank()) return false;

        try {
            new java.math.BigDecimal(input);
            return true;
        }
        catch (NumberFormatException _) {
            return false;
        }
    }

    public static boolean validateNationalId(String id) {
        return id.matches("^[0-9]{4,10}$");
    }

    public static boolean validateFirstName(String name) {
        return name.matches("^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{1,30}$");
    }

    public static boolean validateLastName(String name) {
        return name.matches("^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{1,30}([-\\s][A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{1,30})?$");
    }

    public static boolean validateDithOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return false;

        LocalDate today = LocalDate.now();
        LocalDate earliest = today.minusYears(120);
        LocalDate latest = today;

        return (dateOfBirth.isAfter(earliest) || dateOfBirth.isEqual(earliest))
        && (dateOfBirth.isBefore(latest) || dateOfBirth.isEqual(latest));
    }

}
