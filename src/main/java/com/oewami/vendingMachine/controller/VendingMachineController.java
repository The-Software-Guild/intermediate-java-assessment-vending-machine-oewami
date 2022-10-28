package com.oewami.vendingMachine.controller;

import com.oewami.vendingMachine.dto.Change;
import com.oewami.vendingMachine.dto.Item;
import com.oewami.vendingMachine.service.InsufficientFundsException;
import com.oewami.vendingMachine.service.ItemOutOfStockException;
import com.oewami.vendingMachine.service.VendingMachineService;
import com.oewami.vendingMachine.service.VendingMachineServiceImpl;
import com.oewami.vendingMachine.ui.VendingMachineView;

import java.util.List;

public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineService service;
    private Change balance = new Change();

    public VendingMachineController() {
        this.view = new VendingMachineView();
        this.service = new VendingMachineServiceImpl();
    }

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws ItemOutOfStockException, InsufficientFundsException {
        boolean isContinuing = true;


        while(isContinuing) {
            view.displayBalance(balance.getBalance());
            String input = view.getMenu();

            switch(input) {
                case "1":
                    addMoney();
                    break;
                case "2":
                    //        io.print("2. Show Items");
                    showItems();
                    break;
                case"3":
                    //        io.print("2. Buy Item");
                    buyItem();
                    break;
                case "4":
                    //        io.print("3. Exit");
                    isContinuing = false;
                    break;
                default:
                    view.displayInvalidInput();
            }
        }
        service.exit();
        view.displayRefund(balance);
        view.displayExit();
    }

    public void addMoney() {
        view.getMoney(balance.addFunds(view.addMoney()));
    }

    public void showItems() {
        List<Item> items = service.listAllItems();
        view.displayItems(items);
    }

    public void buyItem() throws ItemOutOfStockException, InsufficientFundsException {
        view.displayItems(service.listAllItems());
        String input = view.buyItem();
        Item item = service.getItem(input);
        if (item != null) {
            service.sellItem(balance, item);
            view.displayChange(balance);
        } else view.displayInvalidInput();
    }
}
