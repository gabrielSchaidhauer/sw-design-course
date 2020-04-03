package com.github.diegopacheco.xunit.testing.bank.model;

import java.math.BigDecimal;

public interface Account {
    BigDecimal getBalance();
    void setBalance(BigDecimal value);
    BigDecimal getTransferFee();
    BigDecimal getWithdrawFee();
}
