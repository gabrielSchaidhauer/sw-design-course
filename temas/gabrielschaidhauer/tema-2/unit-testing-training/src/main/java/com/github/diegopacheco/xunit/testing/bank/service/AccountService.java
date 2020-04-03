package com.github.diegopacheco.xunit.testing.bank.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.diegopacheco.xunit.testing.bank.model.Account;
import com.github.diegopacheco.xunit.testing.bank.model.DefaultAccount;

public class AccountService {

  public void deposit(Account account, BigDecimal value) {
    if (account == null) {
      throw new RuntimeException("Account doesn't exist");
    }
    if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new RuntimeException("You need to deposit a positive value");
    }

    BigDecimal balance = account.getBalance();
    balance = balance.add(value);

    account.setBalance(balance);
  }

  public BigDecimal withdraw(Account account, BigDecimal value) {
    if (account == null) {
      throw new RuntimeException("Account doesn't exist");
    }

    if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new RuntimeException("You need to deposit a positive value");
    }

    BigDecimal taxValue = value.multiply(account.getWithdrawFee());

    if(account.getBalance().compareTo(value.add(taxValue)) < 0) {
      throw new RuntimeException("You don't have enough balance");
    }

    account.setBalance(account.getBalance().subtract(value).subtract(taxValue));

    return value.setScale(2, RoundingMode.FLOOR);
  }

  public void transfer(Account accountFrom, Account accountDestination, BigDecimal value) {
    if (accountFrom == null || accountDestination == null) {
      throw new RuntimeException("Account doesn't exist");
    }

    if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new RuntimeException("You need to deposit a positive value");
    }

    BigDecimal taxValue = value.multiply(accountFrom.getTransferFee());

    if(accountFrom.getBalance().compareTo(value.add(taxValue)) < 0) {
      throw new RuntimeException("You don't have enough balance");
    }
  }
}
