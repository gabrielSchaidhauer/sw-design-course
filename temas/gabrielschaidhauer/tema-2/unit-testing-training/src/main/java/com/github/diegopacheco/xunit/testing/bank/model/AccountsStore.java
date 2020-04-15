package com.github.diegopacheco.xunit.testing.bank.model;

import java.util.*;

public class AccountsStore {
    private Map<Long, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        if(account == null) {
            throw new RuntimeException("You cannot add a empty account");
        }

        this.accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(Long accountNumber) {
        if(accountNumber == null) {
            throw new RuntimeException("You must provide a account number");
        }

        return accounts.get(accountNumber);
    }

    public Long getLastAccountNumber() {
        return this.accounts
                .keySet()
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);
    }
}
