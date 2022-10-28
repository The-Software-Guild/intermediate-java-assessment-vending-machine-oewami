package com.oewami.vendingMachine.ui;

import com.oewami.vendingMachine.dto.Change;
import com.oewami.vendingMachine.dto.Coins;
import com.oewami.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.*;

public class VendingMachineView {

    UserIO io;

    public VendingMachineView() { this.io = new UserIO(); }

    public VendingMachineView(UserIO io) { this.io = io; }

    public String getMenu() {
        io.print("\nMENU");
        io.print("1. Add Money");
        io.print("2. Show Items");
        io.print("3. Buy Item");
        io.print("4. Exit");
        return io.nextLine("Choice: ");
    }

    public BigDecimal addMoney() {
        return io.nextBigDecimal("Funds to add: ");
    }

    public void getMoney(Map<Coins, Integer> money) {
        Set<Coins> coins = money.keySet();
        BigDecimal sum = new BigDecimal("0.0");
        for(Coins coin : coins) {
            io.print(coin + ": " + coin.getValue().multiply(BigDecimal.valueOf(money.get(coin))));
            sum = sum.add(coin.getValue().multiply(BigDecimal.valueOf(money.get(coin))));
        }
        io.print("Total: $" + sum.toString() + "\n");
    }

    public void displayItems(List<Item> inventory) {
        System.out.println(String.format("\n|%-20s|| %7s || %5s", "Product Name", "Cost", "Qty"));
        System.out.println("-".repeat(40));
        for(Item item : inventory) {
            System.out.println(String.format("|%-20s|| $%6s || %5s",
                    item.getName(),
                    item.getCost().toString(),
                    item.getInventory() > 0 ? item.getInventory() : "Out of Stock"));
        }
        System.out.println("-".repeat(40));
    }

    public String buyItem() {
        return io.nextLine("Choice: ");
    }


    public void displayInvalidInput() {
        io.print("Invalid Input\n");
    }

    public void displayBalance(BigDecimal balance) {
        io.print("Balance: $" + balance.toString());
    }

    public void displayExit() {
        io.closeScanner();
        io.print("Exiting Vending Machine");
    }

    public void displayChange(Change balance) {
        Map<Coins, Integer> money = balance.getMoney();
        Set<Coins> coins = money.keySet();
        for(Coins coin : coins) {
            io.print(coin + ": " + coin.getValue().multiply(BigDecimal.valueOf(money.get(coin))));
        }
    }

    public void displayRefund(Change balance) {
        io.print("Refund dispensing: $" + balance.getBalance().negate().toString());
    }
}
