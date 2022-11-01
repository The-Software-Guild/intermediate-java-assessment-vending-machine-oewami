package com.oewami.vendingMachine.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Change {

    private Map<Coins, Integer> change;
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public Change() {this.change = new HashMap<>();}

    public Change(BigDecimal funds) {
        this.change = new HashMap<>();
        addFunds(funds);
    }

    public Map<Coins, Integer> addFunds(BigDecimal funds) {
        BigDecimal total = getBalance().add(funds);
        BigDecimal[] fq = total.divideAndRemainder(Coins.QUARTER.getValue());
        this.quarters = fq[0].intValue();

        BigDecimal[] fd = fq[1].divideAndRemainder(Coins.DIME.getValue());
        this.dimes = fd[0].intValue();

        BigDecimal[] fn = fd[1].divideAndRemainder(Coins.NICKEL.getValue());
        this.nickels = fn[0].intValue();

        BigDecimal[] fp = fn[1].divideAndRemainder(Coins.PENNY.getValue());
        this.pennies = fp[0].intValue();

        this.change.put(Coins.QUARTER, quarters);
        this.change.put(Coins.DIME, dimes);
        this.change.put(Coins.NICKEL, nickels);
        this.change.put(Coins.PENNY, pennies);

        return this.change;
    }

    public Map<Coins, Integer> getChange() {
        return this.change;
    }

    public BigDecimal getBalance() {
        Set<Coins> coins = change.keySet();
        BigDecimal sum = new BigDecimal("0.00");
        for(Coins coin : coins) {
            sum = sum.add(coin.getValue().multiply(BigDecimal.valueOf(change.get(coin))));
        }
        return sum;
    }

    public BigDecimal adjustBalance(BigDecimal purchaseAmount) {
        addFunds(purchaseAmount.negate());
        return getBalance();
    }

}
