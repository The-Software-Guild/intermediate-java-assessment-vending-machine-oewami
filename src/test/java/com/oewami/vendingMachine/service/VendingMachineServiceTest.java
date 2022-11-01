package com.oewami.vendingMachine.service;

import com.oewami.vendingMachine.dao.AuditDaoImpl;
import com.oewami.vendingMachine.dao.FileDaoImpl;
import com.oewami.vendingMachine.dao.VendingMachineDaoImpl;
import com.oewami.vendingMachine.dto.Item;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineServiceTest {

    public static VendingMachineService testService;

    public VendingMachineServiceTest() {}

    @BeforeAll
    public static void setUpClass() {
        testService = new VendingMachineServiceImpl(
                new VendingMachineDaoImpl(
                        new FileDaoImpl("test.txt"),
                        "test.txt",
                        new AuditDaoImpl("audit_test.txt")
                )
        );
    }

    @AfterAll
    public static void tearDownClass() {
        testService.exit();
    }

//    @Test
//    public void listAllItems() {
//
//    }

    @Test
    public void addItem() throws PersistenceException {
        Item item = new Item("Service", new BigDecimal("4.99"), 10);
        testService.addItem(item);
        Item received = testService.getItem("Service");
        assertEquals(item, received);
    }

    @Test
    public void removeItem() throws PersistenceException {
        Item item = new Item("removing from service", new BigDecimal("0.99"), 15);
        testService.addItem(item);
        testService.removeItem(item);
        assertNull(testService.getItem("removing from service"));
    }

    @Test
    public void changeInventoryCount() throws PersistenceException {
        Item item = testService.getItem("Service"); // inv qty = 1
        testService.changeInventoryCount(item, 100);
        assertEquals(100, item.getInventory());
    }

    @Test
    public void sellItemSuccessfully() throws InsufficientFundsException, PersistenceException {// ,ItemOutOfStockException {
        Item item = testService.getItem("Service");
        int prevInventory = item.getInventory();
        BigDecimal prevBalance = testService.getBalance();
        testService.sellItem(item);
        assertEquals(prevInventory - 1, item.getInventory());
        assertEquals(prevBalance.subtract(item.getCost()), testService.getBalance());
    }

    @Test
    public void sellItemWithInsufficientFunds() {
        testService.adjustBalance(testService.getBalance()); // set balance to 0
        Item item = testService.getItem("Service");
        BigDecimal prevBalance = testService.getBalance();
        int prevInventory = item.getInventory();
//        assertThrows(InsufficientFundsException.class, () -> testService.sellItem(funds, item));
        assertEquals(prevInventory, item.getInventory());
        assertEquals(prevBalance, testService.getBalance());
    }

    @Test
    public void sellItemWithNoStock() throws PersistenceException, InsufficientFundsException {
        testService.addFunds(new BigDecimal("100.00"));
        Item noStockItem = new Item("missing", new BigDecimal("5.00"), 0);
        testService.addItem(noStockItem);
        testService.sellItem(noStockItem);
        BigDecimal prevBalance = testService.getBalance();
        int prevInventory = noStockItem.getInventory();
        assertEquals(prevInventory, noStockItem.getInventory());
        assertEquals(prevBalance, testService.getBalance());
    }

}
