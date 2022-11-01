package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.service.PersistenceException;

public interface AuditDao {

    void writeAuditEntry(String entry) throws PersistenceException;
}