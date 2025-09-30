package com.rut.bank.model;

import java.math.BigDecimal;

public class DataValidator {
    public static boolean validateLogin(String login) { return login.matches("[a-zA-Z0-9._]{3,20}"); }

    public static boolean validatePassword(String password) { return password.length() >= 6 && password.length() <= 50; }

    public static boolean validateDeposit(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(new BigDecimal("100000")) < 0;
    }

    public static boolean validateWithdrawal(BigDecimal amount, BigDecimal balance) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0;
    }
}
