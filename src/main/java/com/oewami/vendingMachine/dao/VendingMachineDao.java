package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {

    Item getItem(String name);
    List<Item> getInventory();
    Item addItem(Item item);
    Item removeItem(Item item);
    Item updateInventory(Item item, int count);

    void exit();
}
