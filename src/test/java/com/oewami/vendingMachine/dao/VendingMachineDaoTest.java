package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.dto.Item;

import java.math.BigDecimal;
import com.oewami.vendingMachine.service.PersistenceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class VendingMachineDaoTest {

    public static VendingMachineDao testDao;

    public VendingMachineDaoTest() {}

    @BeforeAll
    public static void setUpClass() {
        testDao = new VendingMachineDaoImpl(
                new FileDaoImpl("test.txt"),
                "test.txt",
                new AuditDaoImpl("audit_test.txt")
        );
    }

    @AfterAll
    public static void tearDownClass() {
        testDao.exit();
    }


    @Test
    public void addItemTest() throws PersistenceException {
        Item item = new Item("Testing", new BigDecimal("1.00"), 5);
        testDao.addItem(item);
        Item received = testDao.getItem("Testing");
        assertEquals(item, received);
    }

    @Test
    public void removeItemTest() throws PersistenceException {
        Item item = new Item("to be removed", new BigDecimal("0.99"), 15);
        testDao.addItem(item);
        testDao.removeItem(item);
        assertNull(testDao.getItem("to be removed"));
    }

    @Test
    public void updateInventoryTest() throws PersistenceException {
        Item item = new Item("to update inv", new BigDecimal("4.00"), 10);
        testDao.addItem(item);
        testDao.updateInventory(item, 4);
        assertEquals(4, item.getInventory());
    }

}
