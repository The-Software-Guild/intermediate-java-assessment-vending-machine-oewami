package com.oewami.vendingMachine;

import com.oewami.vendingMachine.controller.VendingMachineController;
import com.oewami.vendingMachine.dto.Change;
import com.oewami.vendingMachine.service.InsufficientFundsException;
import com.oewami.vendingMachine.service.ItemOutOfStockException;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) throws ItemOutOfStockException, InsufficientFundsException {

        VendingMachineController controller = new VendingMachineController();
        controller.run();

//        System.out.println(25 % 24);

    }
}
