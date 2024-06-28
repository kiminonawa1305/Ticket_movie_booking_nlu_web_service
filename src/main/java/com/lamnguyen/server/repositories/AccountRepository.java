package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getListAccount();

}