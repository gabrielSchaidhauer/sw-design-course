package com.github.diegopacheco.xunit.testing.bank.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountsStoreTest {

    @Test
    public void shouldThrowExceptionWhenAddNullAccount() {
        AccountsStore store = new AccountsStore();
        assertThrows(RuntimeException.class, () -> {
            store.addAccount(null);
        });
    }

    @Test
    public void shouldAddDefaultAccount() {
        AccountsStore store = new AccountsStore();
        store.addAccount(new DefaultAccount(1L));
    }

    @Test
    public void shouldAddSavingstAccount() {
        AccountsStore store = new AccountsStore();
        store.addAccount(new SavingsAccount(1L));
    }

    @Test
    public void shouldThrowExceptionWhenNullAccountNumber() {
        AccountsStore store = new AccountsStore();
        assertThrows(RuntimeException.class, () -> {
            store.getAccount(null);
        });
    }

    @Test
    public void shouldGetNullWhenNoAccountIsAdded() {
        AccountsStore store = new AccountsStore();
        assertNull(store.getAccount(1L));
    }

    @Test
    public void shouldGetNullWhenAccountNumberISWrong() {
        AccountsStore store = new AccountsStore();
        store.addAccount(new DefaultAccount(1L));
        assertNull(store.getAccount(2L));
    }

    @Test
    public void shouldGetAccountWithRightDefaultValue() {
        AccountsStore store = new AccountsStore();
        Account  acc = new DefaultAccount(1L);
        store.addAccount(acc);
        assertEquals(acc, store.getAccount(1L));
    }

    @Test
    public void shouldGetAccountWithRightSavingsValue() {
        AccountsStore store = new AccountsStore();
        Account  acc = new SavingsAccount(1L);
        store.addAccount(acc);
        assertEquals(acc, store.getAccount(1L));
    }

    @Test
    public void shouldGetLastNumberWhenNoAccountIsGiven() {
        AccountsStore store = new AccountsStore();
        assertEquals(0, store.getLastAccountNumber());
    }

    @Test
    public void shouldGetLastNumberWhenOneAccount() {
        AccountsStore store = new AccountsStore();
        store.addAccount(new DefaultAccount(1L));
        assertEquals(1, store.getLastAccountNumber());
    }

    @Test
    public void shouldGetLastNumberWhenMoreThanOneAccount() {
        AccountsStore store = new AccountsStore();
        store.addAccount(new DefaultAccount(1L));
        store.addAccount(new DefaultAccount(2L));
        assertEquals(2, store.getLastAccountNumber());
    }
}
