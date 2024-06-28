package com.lamnguyen.server.services;

import com.lamnguyen.server.models.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account registerAccount(String apiId);

}

