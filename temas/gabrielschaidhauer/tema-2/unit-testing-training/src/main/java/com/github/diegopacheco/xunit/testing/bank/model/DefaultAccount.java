package com.github.diegopacheco.xunit.testing.bank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DefaultAccount implements Account{

  private BigDecimal balance = BigDecimal.ZERO;
  private static final BigDecimal TRANSFERENCE_FEE = new BigDecimal("0.05");
  private static final BigDecimal WITHDRAW_FEE = BigDecimal.ZERO;

  @Override
  public BigDecimal getTransferFee() {
    return TRANSFERENCE_FEE;
  }

  @Override
  public BigDecimal getWithdrawFee() {
    return WITHDRAW_FEE;
  }

  @Override
  public BigDecimal getBalance() {
    return balance.setScale(2, RoundingMode.FLOOR);
  }

  public void setBalance(BigDecimal balance) {
    if (balance != null && balance.compareTo(BigDecimal.ZERO) > 0) {
    	this.balance = balance;
    }
  }
}
