package com.oewami.vendingMachine.service;

import com.oewami.vendingMachine.dto.Change;
import com.oewami.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {
    public Item getItem(String name);

    public List<Item> listAllItems();

    public Item addItem(Item item);

    public Item removeItem(Item item);

    public Item changeInventoryCount(Item item, int newCount);

    public BigDecimal sellItem(Change totalFunds, Item item);

    void exit();
}
