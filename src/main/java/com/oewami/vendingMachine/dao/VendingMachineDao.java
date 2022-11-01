package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.dto.Item;
import com.oewami.vendingMachine.service.PersistenceException;

import java.util.List;

public interface VendingMachineDao {

    Item getItem(String name);
    List<Item> getInventory();
    void addItem(Item item) throws PersistenceException;
    void removeItem(Item item) throws PersistenceException;
    void updateInventory(Item item, int count) throws PersistenceException;

    void exit();
}
