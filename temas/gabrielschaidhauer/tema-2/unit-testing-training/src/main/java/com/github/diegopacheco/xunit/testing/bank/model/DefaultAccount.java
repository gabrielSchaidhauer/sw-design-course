package com.github.diegopacheco.xunit.testing.bank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class DefaultAccount implements Account{

  private BigDecimal balance = BigDecimal.ZERO;
  private static final BigDecimal TRANSFERENCE_FEE = new BigDecimal("0.05");
  private static final BigDecimal WITHDRAW_FEE = BigDecimal.ZERO;
  private Long accountNumber;

  public DefaultAccount (Long accountNumber) {
    if(accountNumber == null) {
      throw new RuntimeException("Account number must not be null");
    }

    this.accountNumber = accountNumber;
  }

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

  @Override
  public Long getAccountNumber() {
    return accountNumber;
  }
}
