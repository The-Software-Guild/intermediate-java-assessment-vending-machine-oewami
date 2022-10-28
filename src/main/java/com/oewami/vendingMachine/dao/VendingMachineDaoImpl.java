package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.dto.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoImpl implements VendingMachineDao{

    private Map<String, Item> inventory;
    private String ITEM_FILE = "items.txt";
    private FileDao fileDao;

    public VendingMachineDaoImpl() {
        this.fileDao = new FileDaoImpl();
        this.inventory = fileDao.readFile(ITEM_FILE);
    }

    public VendingMachineDaoImpl(FileDao fileDao, String file) {
        this.fileDao = fileDao;
        this.inventory = fileDao.readFile(file);
    }

    @Override
    public Item getItem(String name) {
        return inventory.get(name);
    }

    @Override
    public List<Item> getInventory() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public Item addItem(Item item) {
        inventory.put(item.getName(), item);
        return item;
    }

    @Override
    public Item removeItem(Item item) {
        inventory.remove(item.getName());
        return item;
    }

    @Override
    public Item updateInventory(Item item, int count) {
        item.setInventory(count);
        return item;
    }

    public void exit() {
        fileDao.writeFile(getInventory());
    }
}
