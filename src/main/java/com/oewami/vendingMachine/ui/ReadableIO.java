package com.oewami.vendingMachine.ui;

import java.math.BigDecimal;

public interface ReadableIO {

    public void print(String message);

    public String nextLine(String prompt);

    public BigDecimal nextBigDecimal(String prompt);
}
