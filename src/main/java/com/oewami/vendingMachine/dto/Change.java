package com.oewami.vendingMachine.dto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Change {

    private Map<Coins, Integer> money;
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public Change() {
        this.money = new HashMap<>();
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

        this.money.put(Coins.QUARTER, quarters);
        this.money.put(Coins.DIME, dimes);
        this.money.put(Coins.NICKEL, nickels);
        this.money.put(Coins.PENNY, pennies);

        return this.money;
    }

    public Map<Coins, Integer> getMoney() {
        return this.money;
    }

    public BigDecimal getBalance() {
        Set<Coins> coins = money.keySet();
        BigDecimal sum = new BigDecimal("0.00");
        for(Coins coin : coins) {
            sum = sum.add(coin.getValue().multiply(BigDecimal.valueOf(money.get(coin))));
        }
        return sum;
    }

    public BigDecimal adjustBalance(BigDecimal purchaseAmount) {
        addFunds(purchaseAmount.negate());
        return getBalance();
    }

}
