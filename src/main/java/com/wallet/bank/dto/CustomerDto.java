package com.wallet.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long customerId;
    private String name;
    private String email;
    private String imageUrl;
    private Timestamp createdAt;
    private Set<AccountDto> accounts;
}
