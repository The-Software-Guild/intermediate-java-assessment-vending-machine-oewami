package com.oewami.vendingMachine.service;

import com.oewami.vendingMachine.dto.Change;
import com.oewami.vendingMachine.dto.Coins;
import com.oewami.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineService {
    public Item getItem(String name);

    public List<Item> listAllItems();

    public void addItem(Item item) throws PersistenceException;

    public void removeItem(Item item) throws PersistenceException;

    public void changeInventoryCount(Item item, int newCount) throws PersistenceException;

    public BigDecimal sellItem(Item item) throws InsufficientFundsException, PersistenceException;

    void exit();

    void addFunds(BigDecimal money);

    Change getChange();

    BigDecimal getBalance();

    void adjustBalance(BigDecimal balance);
}
