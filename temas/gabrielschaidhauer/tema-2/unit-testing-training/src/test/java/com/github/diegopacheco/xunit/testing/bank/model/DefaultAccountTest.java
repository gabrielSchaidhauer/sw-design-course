package com.github.diegopacheco.xunit.testing.bank.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class DefaultAccountTest {

  @Test
  void shouldHaveBalanceGreaterThanZeroWithNegativeInput() {
    DefaultAccount defaultAccount = new DefaultAccount();
    defaultAccount.setBalance(new BigDecimal("-10"));
    assertTrue(defaultAccount.getBalance().doubleValue() >= 0);
  }

  @Test
  void shouldHaveBalanceGreaterThanEqualZeroWithNullInput() {
    DefaultAccount account = new DefaultAccount();
    account.setBalance(null);
    assertTrue(account.getBalance().doubleValue() >= 0);
  }
  
  @Test
  void shouldHaveBalanceGreaterThanZeroWithPositiveInput() {
    DefaultAccount defaultAccount = new DefaultAccount();
    defaultAccount.setBalance(new BigDecimal("10"));
    assertTrue(defaultAccount.getBalance().doubleValue()>=0);
  }
  
  @Test
  void shouldHaveBalanceGreaterThanZeroWithzeroInput() {
    DefaultAccount defaultAccount = new DefaultAccount();
    defaultAccount.setBalance(BigDecimal.ZERO);
    assertTrue(defaultAccount.getBalance().doubleValue()>=0);
  }
  
  @Test
  void shouldHaveBalanceWithCorrectValue() {
	 
    DefaultAccount defaultAccount = new DefaultAccount();
    defaultAccount.setBalance(new BigDecimal("1000000.10"));
    assertEquals(defaultAccount.getBalance(),new BigDecimal("1000000.10"));
  }
  
  @Test
  void shouldHaveBalanceWithBigValue() {
	 
    DefaultAccount defaultAccount = new DefaultAccount();
    defaultAccount.setBalance(new BigDecimal("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.00"));
    assertEquals(defaultAccount.getBalance(),new BigDecimal("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.00"));
  }
}

