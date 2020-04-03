package com.github.diegopacheco.xunit.testing.bank.service;

import com.github.diegopacheco.xunit.testing.bank.model.Account;
import com.github.diegopacheco.xunit.testing.bank.model.DefaultAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

import com.github.diegopacheco.xunit.testing.bank.model.SavingsAccount;
import org.junit.jupiter.api.Test;

public class AccountServiceTest {

  @Test()
  void shouldThowErrorWithNullAccount() {
    AccountService service = new AccountService();

    assertThrows(
        RuntimeException.class,
        () -> service.deposit(null, new BigDecimal("2500")));
  }

  @Test()
  void shouldHaveDepositInDefaultAccount() {
    AccountService service = new AccountService();
    DefaultAccount defaultAccount = new DefaultAccount();
    defaultAccount.setBalance(new BigDecimal("20.01"));

    service.deposit(defaultAccount, new BigDecimal("2500.00"));
    assertEquals (new BigDecimal("2520.01").setScale(2, RoundingMode.HALF_EVEN), defaultAccount.getBalance().setScale(2, RoundingMode.HALF_EVEN));
  }

  @Test()
  void shouldHaveDepositInSavingsAccount() {
    AccountService service = new AccountService();
    SavingsAccount account = new SavingsAccount();
    account.setBalance(new BigDecimal("20.01"));

    service.deposit(account, new BigDecimal("2500.00"));
    assertEquals (new BigDecimal("2520.01").setScale(2, RoundingMode.HALF_EVEN), account.getBalance().setScale(2, RoundingMode.HALF_EVEN));
  }
  
  @Test()
  void shouldThrowErrorWitNegativeValueDefaultAccount() {
    AccountService service = new AccountService();
    DefaultAccount defaultAccount = new DefaultAccount();

    assertThrows(
        RuntimeException.class,
        () -> service.deposit(defaultAccount, new BigDecimal("-2500")));
  }

  @Test()
  void shouldThrowErrorWitNegativeValueSavingsAccount() {
    AccountService service = new AccountService();
    SavingsAccount account = new SavingsAccount();

    assertThrows(
            RuntimeException.class,
            () -> service.deposit(account, new BigDecimal("-2500")));
  }


  @Test()
  void shouldThrowErrorWitZeroValueDefaultAccount() {
    AccountService service = new AccountService();
    DefaultAccount defaultAccount = new DefaultAccount();

    assertThrows(
        RuntimeException.class,
        () -> service.deposit(defaultAccount, new BigDecimal(0)));
  }

  @Test()
  void shouldThrowErrorWitZeroValueSavingsAccount() {
    AccountService service = new AccountService();
    SavingsAccount account = new SavingsAccount();

    assertThrows(
            RuntimeException.class,
            () -> service.deposit(account, new BigDecimal(0)));
  }

  @Test()
  void shouldThrowErrorWitNullValueSavingsAccount() {
    AccountService service = new AccountService();
    SavingsAccount account = new SavingsAccount();

    assertThrows(
            RuntimeException.class,
            () -> service.deposit(account, null));
  }

  @Test()
  void shouldThrowErrorWitNullValueDefaultAccount() {
    AccountService service = new AccountService();
    DefaultAccount account = new DefaultAccount();

    assertThrows(
            RuntimeException.class,
            () -> service.deposit(account, null));
  }
  
  @Test()
  void shouldHaveDepositBIgNumberInAccountDefaultAccount() {
  
    AccountService service = new AccountService();
    DefaultAccount defaultAccount = new DefaultAccount();
    defaultAccount.setBalance(new BigDecimal("29999999999999999999999999999999999999999999999999999999990000000000000000000000000000000000000000000000000000000000000000009.01"));

    service.deposit(defaultAccount, new BigDecimal("0.50"));
    assertEquals (new BigDecimal("29999999999999999999999999999999999999999999999999999999990000000000000000000000000000000000000000000000000000000000000000009.51").setScale(2, RoundingMode.FLOOR), defaultAccount.getBalance().setScale(2, RoundingMode.FLOOR));

  }

  @Test()
  void shouldHaveDepositBIgNumberInAccountSavingsAccount() {

    AccountService service = new AccountService();
    SavingsAccount account = new SavingsAccount();
    account.setBalance(new BigDecimal("29999999999999999999999999999999999999999999999999999999990000000000000000000000000000000000000000000000000000000000000000009.01"));

    service.deposit(account, new BigDecimal("0.50"));
    assertEquals (new BigDecimal("29999999999999999999999999999999999999999999999999999999990000000000000000000000000000000000000000000000000000000000000000009.51").setScale(2, RoundingMode.FLOOR), account.getBalance().setScale(2, RoundingMode.FLOOR));

  }

  @Test
  void shouldDepositSavingsAndGetNewValue() {
    AccountService service = new AccountService();
    SavingsAccount account = new SavingsAccount();
    service.deposit(account, new BigDecimal("100"));
    account.lastBalanceChangeDate = account.lastBalanceChangeDate.minus(1, ChronoUnit.MINUTES);
    assertEquals(new BigDecimal("122.00"), account.getBalance());
  }

  @Test
  void shouldDepositSavingsAndGetNewValueafterTwoSeconds() {
    AccountService service = new AccountService();
    SavingsAccount account = new SavingsAccount();
    service.deposit(account, new BigDecimal("100"));
    account.lastBalanceChangeDate = account.lastBalanceChangeDate.minus(2, ChronoUnit.MINUTES);
    assertEquals(new BigDecimal("144.00"), account.getBalance());
  }

  @Test
  void shouldThrowErrorWhenTransgerNegativeValueDefaultAccount() {
    AccountService service = new AccountService();
    Account accountOrigin = new DefaultAccount();
    Account accountDestination = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, new BigDecimal("-10"));
    });
  }

  @Test
  void shouldThrowErrorWhenTransferNegativeValueSavingsAccount() {
    AccountService service = new AccountService();
    Account accountOrigin = new SavingsAccount();
    Account accountDestination = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, new BigDecimal("-10"));
    });
  }

  @Test
  void shouldThrowErrorWhenTansferZeroValueSavingsAccount() {
    AccountService service = new AccountService();
    Account accountOrigin = new SavingsAccount();
    Account accountDestination = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, new BigDecimal("0"));
    });
  }

  @Test
  void shouldThrowErrorWhenTransferZeroValueDefaultAccount() {
    AccountService service = new AccountService();
    Account accountOrigin = new DefaultAccount();
    Account accountDestination = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, new BigDecimal("0"));
    });
  }

  @Test
  void shouldThrowErrorWhenTransferNullValueSavingsAccount() {
    AccountService service = new AccountService();
    Account accountOrigin = new SavingsAccount();
    Account accountDestination = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, null);
    });
  }

  @Test
  void shouldThrowErrorWhenTransferNullValueDefaultAccount() {
    AccountService service = new AccountService();
    Account accountOrigin = new DefaultAccount();
    Account accountDestination = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, null);
    });
  }

  @Test
  void shouldThrowErrorSavingsAccountOriginIsNull() {
    AccountService service = new AccountService();
    Account accountOrigin = new SavingsAccount();
    Account accountDestination = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(null, accountDestination, new BigDecimal("10"));
    });
  }

  @Test
  void shouldThrowErrorSavingsAccountDestinationIsNull() {
    AccountService service = new AccountService();
    Account accountOrigin = new SavingsAccount();
    Account accountDestination = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountDestination, null, new BigDecimal("10"));
    });
  }

  @Test
  void shouldThrowErrorWhenDefaultAccountOriginIsNull() {
    AccountService service = new AccountService();
    Account accountOrigin = new DefaultAccount();
    Account accountDestination = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(null, accountDestination, new BigDecimal("10"));
    });
  }

  @Test
  void shouldThrowErrorWhenDefaultAccountDestinationIsNull() {
    AccountService service = new AccountService();
    Account accountOrigin = new DefaultAccount();
    Account accountDestination = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, null, new BigDecimal("10"));
    });
  }

  @Test
  void shouldThrowErrorWhenInsuficientBalanceDefaultAccountTransfer() {
    AccountService service = new AccountService();
    Account accountOrigin = new DefaultAccount();
    Account accountDestination = new DefaultAccount();
    service.deposit(accountOrigin, new BigDecimal("10"));
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, new BigDecimal("11"));
    });
  }

  @Test
  void shouldThrowErrorWhenInsuficientBalanceSavingsAccountTransfer() {
    AccountService service = new AccountService();
    Account accountOrigin = new SavingsAccount();
    Account accountDestination = new SavingsAccount();
    service.deposit(accountOrigin, new BigDecimal("10"));
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, new BigDecimal("11"));
    });
  }

  @Test
  void shouldThrowErrorWhenInsuficientBalanceFeeDefaultAccountTransfer() {
    AccountService service = new AccountService();
    Account accountOrigin = new DefaultAccount();
    Account accountDestination = new SavingsAccount();
    service.deposit(accountOrigin, new BigDecimal("100"));
    assertThrows(RuntimeException.class, () -> {
      service.transfer(accountOrigin, accountDestination, new BigDecimal("100"));
    });
  }

 /* @Test
  void shouldWithdrawValueDefaultAccount() {
    AccountService service = new AccountService();
    Account account = new DefaultAccount();
    service.deposit(account, new BigDecimal("100"));
    BigDecimal withdrawed = service.withdraw(account, new BigDecimal("50"));
    BigDecimal currentBalance = account.getBalance();

    assertEquals(new BigDecimal("50.00"), withdrawed);
    assertEquals(new BigDecimal("50.00"), currentBalance);
  }

  @Test
  void shouldWithdrawValueSavingsAccount() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    service.deposit(account, new BigDecimal("100"));
    BigDecimal withdrawed = service.withdraw(account, new BigDecimal("50"));
    BigDecimal currentBalance = account.getBalance();

    assertEquals(new BigDecimal("50.00"), withdrawed);
    assertEquals(new BigDecimal("49.00"), currentBalance);
  }*/

  @Test
  void shouldThrowErrorWhenWithdrawNegativeValueDefaultAccount() {
    AccountService service = new AccountService();
    Account account = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, new BigDecimal("-10"));
    });
  }

  @Test
  void shouldThrowErrorWhenWithdrawNegativeValueSavingsAccount() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, new BigDecimal("-10"));
    });
  }

  @Test
  void shouldThrowErrorWhenWithdrawZeroValueSavingsAccount() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, new BigDecimal("0"));
    });
  }

  @Test
  void shouldThrowErrorWhenWithdrawZeroValueDefaultAccount() {
    AccountService service = new AccountService();
    Account account = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, new BigDecimal("0"));
    });
  }

  @Test
  void shouldThrowErrorWhenWithdrawNullValueSavingsAccount() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, null);
    });
  }

  @Test
  void shouldThrowErrorWhenWithdrawNullValueDefaultAccount() {
    AccountService service = new AccountService();
    Account account = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, null);
    });
  }

  @Test
  void shouldThrowErrorSavingsAccountIsNull() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(null, new BigDecimal("10"));
    });
  }

  @Test
  void shouldThrowErrorWhenDefaultAccountIsNull() {
    AccountService service = new AccountService();
    Account account = new DefaultAccount();
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(null, new BigDecimal("10"));
    });
  }

  @Test
  void shouldThrowErrorWhenInsuficientBalanceDefaultAccount() {
    AccountService service = new AccountService();
    Account account = new DefaultAccount();
    service.deposit(account, new BigDecimal("10"));
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, new BigDecimal("11"));
    });
  }

  @Test
  void shouldThrowErrorWhenInsuficientBalanceSavingsAccount() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    service.deposit(account, new BigDecimal("10"));
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, new BigDecimal("11"));
    });
  }

  @Test
  void shouldThrowErrorWhenInsuficientBalanceFeeSavingsAccount() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    service.deposit(account, new BigDecimal("100"));
    assertThrows(RuntimeException.class, () -> {
      service.withdraw(account, new BigDecimal("100"));
    });
  }

  @Test
  void shouldWithdrawValueDefaultAccount() {
    AccountService service = new AccountService();
    Account account = new DefaultAccount();
    service.deposit(account, new BigDecimal("100"));
    BigDecimal withdrawed = service.withdraw(account, new BigDecimal("50"));
    BigDecimal currentBalance = account.getBalance();

    assertEquals(new BigDecimal("50.00"), withdrawed);
    assertEquals(new BigDecimal("50.00"), currentBalance);
  }

  @Test
  void shouldWithdrawValueSavingsAccount() {
    AccountService service = new AccountService();
    Account account = new SavingsAccount();
    service.deposit(account, new BigDecimal("100"));
    BigDecimal withdrawed = service.withdraw(account, new BigDecimal("50"));
    BigDecimal currentBalance = account.getBalance();

    assertEquals(new BigDecimal("50.00"), withdrawed);
    assertEquals(new BigDecimal("49.00"), currentBalance);
  }
}
