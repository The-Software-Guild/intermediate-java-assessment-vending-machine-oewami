package com.oewami.vendingMachine.service;

import com.oewami.vendingMachine.dao.VendingMachineDao;
import com.oewami.vendingMachine.dao.VendingMachineDaoImpl;
import com.oewami.vendingMachine.dto.Change;
import com.oewami.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingMachineDao dao;
    private Change change = new Change();

    public VendingMachineServiceImpl() {
        this.dao = new VendingMachineDaoImpl();
    }

    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Item getItem(String name) {
        return dao.getItem(name);
    }

    @Override
    public List<Item> listAllItems() {
        return dao.getInventory();
    }

    @Override
    public void addItem(Item item) throws PersistenceException {
        dao.addItem(item);
    }

    @Override
    public void removeItem(Item item) throws PersistenceException {
        dao.removeItem(item);
    }

    @Override
    public void changeInventoryCount(Item item, int newCount) throws PersistenceException {
        dao.updateInventory(item, newCount);
    }

    @Override
    public BigDecimal sellItem(Item item) throws PersistenceException {
//        0 : if value of this BigDecimal is equal to that of BigDecimal object passed as parameter.
//1 : if value of this BigDecimal is greater than that of BigDecimal object passed as parameter.
//-1 : if value of this BigDecimal is less than that of BigDecimal object passed as parameter.
        if(change.getBalance().compareTo(item.getCost()) >= 0) {
            if(item.getInventory() > 0) {
                dao.updateInventory(item, item.getInventory() - 1);
                return change.adjustBalance(item.getCost());
            } else {
                System.out.println(item.getName() + " is OUT OF STOCK");
                return change.getBalance();
//                throw new ItemOutOfStockException(item.getName() + " is out of stock");
            }
        } else {
            // display balance of user
            // display how much more money the user needs to buy item.
            BigDecimal difference = item.getCost().subtract(change.getBalance());
            System.out.println("You have: $" + change.getBalance());
            System.out.println("Needed to Purchase " + item.getName() + ": $" + difference.toString() + "\n");
//            throw new InsufficientFundsException("DECLINED. Insufficient Funds.");
            return change.getBalance();
        }
    }

    @Override
    public void addFunds(BigDecimal money) {
        change.addFunds(money);
    }

    @Override
    public Change getChange() {
        return change;
    }

    public BigDecimal getBalance() {
        return change.getBalance();
    }

    @Override
    public void adjustBalance(BigDecimal cost) {
        change.adjustBalance(cost);
    }

    public void exit() {
        dao.exit();
    }
}
