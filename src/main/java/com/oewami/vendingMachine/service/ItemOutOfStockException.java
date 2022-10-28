package com.oewami.vendingMachine.service;

public class ItemOutOfStockException extends Exception {

    public ItemOutOfStockException(String message) { super(message); }

    public ItemOutOfStockException(String message, Throwable cause) { super(message, cause); }
}
