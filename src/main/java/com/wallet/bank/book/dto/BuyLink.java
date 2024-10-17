package com.wallet.bank.book.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BuyLink {
    private String name;
    private String url;
}
