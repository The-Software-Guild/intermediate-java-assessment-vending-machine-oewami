package com.oewami.vendingMachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserIO implements ReadableIO {

    final private Scanner scanner = new Scanner(System.in);

    /**
     * Outputs a message to the console
     * @param message message to be displayed
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Outputs a message to the console. Takes input from console.
     *
     * @param prompt message displayed to the console
     * @return The user's input
     */
    @Override
    public String nextLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public BigDecimal nextBigDecimal(String prompt) {
        System.out.println(prompt);
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        String input = scanner.nextLine();
        if(pattern.matcher(input).matches()) {
            return new BigDecimal(input);
        } else {
            System.out.println("Invalid input.");
            return new BigDecimal("0.00");
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
