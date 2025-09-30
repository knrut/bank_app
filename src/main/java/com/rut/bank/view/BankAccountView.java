package com.rut.bank.view;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankAccountView {
    private final Scanner scanner = new Scanner(System.in);

    public String askForInput(String message) {
        printMessage(message);
        return scanner.nextLine();
    }

    public BigDecimal askForAmount(String message) {
        String input = askForInput(message);
        try {
            return new BigDecimal(input);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public BigDecimal parseStringToBigDecimal(String input) {
        try {
            return new BigDecimal(input);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
