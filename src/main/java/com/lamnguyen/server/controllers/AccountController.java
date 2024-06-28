package com.lamnguyen.server.controllers;

import com.lamnguyen.server.models.entity.Account;
import com.lamnguyen.server.models.response.APIResponse;
import com.lamnguyen.server.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/accounts/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @PostMapping("/register")
    public APIResponse<Integer> registerAccount(@RequestBody Map<String, String> body) {
        String apiId = body.get("apiId");
        Account newAccount = accountService.registerAccount(apiId);
        if (newAccount == null) throw new NullPointerException("Cannot register user");
        return APIResponse.<Integer>builder()
                .message("Register successfully")
                .status(202)
                .data(newAccount.getId())
                .build();
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDetails) {
//        AccountDTO updatedAccount = accountService.updateAccount(id, accountDetails);
//
//        if (updatedAccount != null) {
//            return ResponseEntity.ok(updatedAccount);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


}
