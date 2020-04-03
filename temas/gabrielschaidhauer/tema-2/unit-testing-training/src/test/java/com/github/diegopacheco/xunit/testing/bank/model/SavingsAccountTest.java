package com.github.diegopacheco.xunit.testing.bank.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SavingsAccountTest {

  @Test
  void shouldHaveBalanceGreaterThanEqualZeroWithNegativeInput() {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("-10"));
    assertTrue(savingsAccount.getBalance().doubleValue() >= 0);
  }

  @Test
  void shouldHaveBalanceGreaterThanEqualZeroWithNullInput() {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(null);
    assertTrue(savingsAccount.getBalance().doubleValue() >= 0);
  }
  
  @Test
  void shouldHaveBalanceGreaterThanEqualZeroWithPositiveInput() {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("10"));
    assertTrue(savingsAccount.getBalance().doubleValue()>=0);
  }
  
  @Test
  void shouldHaveBalanceGreaterEqualThanZeroWithzeroInput() {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("0"));
    assertTrue(savingsAccount.getBalance().doubleValue()>=0);
  }
  
  @Test
  void shouldHaveBalanceWithCorrectWithNoTimePassedValue() {
	 
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("1000000.10"));
    assertEquals(savingsAccount.getBalance(),new BigDecimal("1000000.10"));
  }
  
  @Test
  void shouldHaveBalanceWithBigValueWithNoTimePassed() {
	 
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.00"));
    assertEquals(savingsAccount.getBalance(),new BigDecimal("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.00"));
  }

  @Test
  void shouldHaveBalanceIncreasedOverOneMinute () {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("100"));
    savingsAccount.lastBalanceChangeDate = LocalDateTime.now().minus(1, ChronoUnit.MINUTES);
    assertEquals(new BigDecimal("122.00"), savingsAccount.getBalance());
  }

  @Test
  void shouldHaveBalanceIncreasedOverTwoMinutes () {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("100"));
    savingsAccount.lastBalanceChangeDate = LocalDateTime.now().minus(2, ChronoUnit.MINUTES);
    assertEquals(new BigDecimal("144.00"), savingsAccount.getBalance());
  }

  @Test
  void shouldHaveBalanceIncreasedwithZeroBalance () {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setBalance(new BigDecimal("0"));
    savingsAccount.lastBalanceChangeDate = LocalDateTime.now().minus(2, ChronoUnit.MINUTES);
    assertEquals(new BigDecimal("0.00"), savingsAccount.getBalance());
  }
}

