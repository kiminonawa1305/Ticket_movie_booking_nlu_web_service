package com.lamnguyen.server.services.impl;

import com.lamnguyen.server.models.entity.Account;
import com.lamnguyen.server.repositories.AccountRepository;
import com.lamnguyen.server.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account registerAccount(String apiId) {
        return accountRepository.save(Account.builder().apiId(apiId).build());
    }
}
