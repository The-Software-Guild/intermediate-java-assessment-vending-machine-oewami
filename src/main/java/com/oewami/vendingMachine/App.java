package com.oewami.vendingMachine;

import com.oewami.vendingMachine.controller.VendingMachineController;
import com.oewami.vendingMachine.service.InsufficientFundsException;
import com.oewami.vendingMachine.service.ItemOutOfStockException;
import com.oewami.vendingMachine.service.PersistenceException;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) throws ItemOutOfStockException, InsufficientFundsException, PersistenceException {

        VendingMachineController controller = new VendingMachineController();
        controller.run();


    }
}
