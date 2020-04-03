package com.github.diegopacheco.xunit.testing.bank.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class SavingsAccount implements Account {

    private BigDecimal balance = BigDecimal.ZERO;
    // Public for testing purposes
    public LocalDateTime lastBalanceChangeDate = LocalDateTime.now();
    private static final BigDecimal TRANSFER_FEE = BigDecimal.ZERO;
    private static final BigDecimal WITHDRAW_FEE = new BigDecimal("0.02");
    private static final BigDecimal INCREASE_INDEX = new BigDecimal("0.22");

    @Override
    public BigDecimal getBalance() {
        BigDecimal increaseIndex = INCREASE_INDEX.multiply(BigDecimal.valueOf(getTimeDifferenceInCompleteMinutes()));
        BigDecimal valueToIncrease = balance.multiply(increaseIndex);
        return balance.add(valueToIncrease).setScale(2, RoundingMode.FLOOR);
    }

    public void setBalance(BigDecimal balance) {
        if (balance != null && balance.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = balance;
            this.lastBalanceChangeDate = LocalDateTime.now();
        }
    }

    @Override
    public BigDecimal getTransferFee() {
        return TRANSFER_FEE;
    }

    @Override
    public BigDecimal getWithdrawFee() {
        return WITHDRAW_FEE;
    }

    private long getTimeDifferenceInCompleteMinutes() {
        return Duration.between(this.lastBalanceChangeDate, LocalDateTime.now()).toMinutes();
    }
}
