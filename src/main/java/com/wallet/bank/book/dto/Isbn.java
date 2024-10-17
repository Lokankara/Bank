package com.wallet.bank.book.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Isbn {
    private String isbn10;
    private String isbn13;
}
