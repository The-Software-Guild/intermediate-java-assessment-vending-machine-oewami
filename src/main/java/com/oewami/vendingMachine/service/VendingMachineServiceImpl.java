package com.oewami.vendingMachine.service;

import com.oewami.vendingMachine.dao.VendingMachineDao;
import com.oewami.vendingMachine.dao.VendingMachineDaoImpl;
import com.oewami.vendingMachine.dto.Change;
import com.oewami.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingMachineDao dao;

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
    public Item addItem(Item item) {
        dao.addItem(item);
        return null;
    }

    @Override
    public Item removeItem(Item item){
        dao.removeItem(item);
        return null;
    }

    @Override
    public Item changeInventoryCount(Item item, int newCount){
        dao.updateInventory(item, newCount);
        return null;
    }

    @Override
    public BigDecimal sellItem(Change totalFunds, Item item) {//throws InsufficientFundsException, ItemOutOfStockException {
//        0 : if value of this BigDecimal is equal to that of BigDecimal object passed as parameter.
//1 : if value of this BigDecimal is greater than that of BigDecimal object passed as parameter.
//-1 : if value of this BigDecimal is less than that of BigDecimal object passed as parameter.
        if(totalFunds.getBalance().compareTo(item.getCost()) >= 0) {
            if(item.getInventory() > 0) {
                changeInventoryCount(item, item.getInventory() - 1);
                return totalFunds.adjustBalance(item.getCost());

            } else {
                System.out.println(item.getName() + " is OUT OF STOCK");
                return totalFunds.getBalance();
//                throw new ItemOutOfStockException(item.getName() + " is out of stock");
            }
        } else {
            // display balance of user
            // display how much more money the user needs to buy item.
            BigDecimal difference = item.getCost().subtract(totalFunds.getBalance());
            System.out.println("You have: $" + totalFunds.getBalance());
            System.out.println("Needed to Purchase " + item.getName() + ": $" + difference.toString() + "\n");
//            throw new InsufficientFundsException("DECLINED. Insufficient Funds.");
            return totalFunds.getBalance();
        }
    }

    public void exit() {
        dao.exit();
    }
}
