package com.github.diegopacheco.xunit.testing.bank.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.diegopacheco.xunit.testing.bank.model.Account;
import com.github.diegopacheco.xunit.testing.bank.model.AccountsStore;
import com.github.diegopacheco.xunit.testing.bank.model.DefaultAccount;
import com.github.diegopacheco.xunit.testing.bank.model.SavingsAccount;

public class AccountService {

  private AccountsStore store = new AccountsStore();

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

  public Long createAccount() {
    Account account = new DefaultAccount(store.getLastAccountNumber() + 1L);
    store.addAccount(account);
    return account.getAccountNumber();
  }

  public Long createSavingsAccount() {
    Account account = new SavingsAccount(store.getLastAccountNumber() + 1L);
    store.addAccount(account);
    return account.getAccountNumber();
  }

  public Account getAccount(Long accountNumber) {
    if(accountNumber == null) {
      throw new RuntimeException("Account number must not be null");
    }
    return store.getAccount(accountNumber);
  }
}
