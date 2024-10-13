package com.wallet.bank.web;

import com.wallet.bank.domain.Account;
import com.wallet.bank.dto.AccountDto;
import com.wallet.bank.mapper.AccountMapper;
import com.wallet.bank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts()
                .stream().map(AccountMapper::toDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(AccountMapper.toDto(accountService
                .createAccount(account)), HttpStatus.CREATED);
    }
}
