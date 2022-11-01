package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.service.PersistenceException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditDaoImpl implements AuditDao {

    private static String AUDIT_FILE;

    public AuditDaoImpl() {
        AUDIT_FILE = "audit.txt";
    }

    public AuditDaoImpl(String file) {
        AUDIT_FILE = file;
    }

    // purchase name of item with qty
    // track creation and deletion of objects
    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(AUDIT_FILE, true));
            LocalDateTime timestamp = LocalDateTime.now();
            writer.write(timestamp.toString() + ": " + entry);
            writer.write(System.lineSeparator());
            writer.close();
        } catch(IOException e) {
            throw new PersistenceException(AUDIT_FILE + " not found");
        }

    }
}
