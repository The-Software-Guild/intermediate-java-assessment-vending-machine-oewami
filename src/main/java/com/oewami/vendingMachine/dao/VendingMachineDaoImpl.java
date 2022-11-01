package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.dto.Item;
import com.oewami.vendingMachine.service.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoImpl implements VendingMachineDao{

    private Map<String, Item> inventory;
    private String ITEM_FILE = "items.txt";
    private FileDao fileDao;
    private AuditDao auditDao;

    public VendingMachineDaoImpl() {
        this.fileDao = new FileDaoImpl();
        this.inventory = fileDao.readFile(ITEM_FILE);
        this.auditDao = new AuditDaoImpl();
    }

    public VendingMachineDaoImpl(FileDao fileDao, String file, AuditDao auditDao) {
        this.fileDao = fileDao;
        this.inventory = fileDao.readFile(file);
        this.auditDao = auditDao;
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
    public void addItem(Item item) throws PersistenceException {
        inventory.put(item.getName(), item);
        auditDao.writeAuditEntry("ADDED " + item.getName() + " to inventory");
    }

    @Override
    public void removeItem(Item item) throws PersistenceException {
        inventory.remove(item.getName());
        auditDao.writeAuditEntry("DELETED " + item.getName() + " from inventory");
    }

    @Override
    public void updateInventory(Item item, int count) throws PersistenceException {
        auditDao.writeAuditEntry(item.getName() + " count from " + item.getInventory() + " to " + count);
        item.setInventory(count);
    }

    public void exit() {
        fileDao.writeFile(getInventory());
    }
}
