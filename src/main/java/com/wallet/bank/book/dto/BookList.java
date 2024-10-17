package com.wallet.bank.book.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookList {
    private String list_name;
    private String display_name;
    private String list_name_encoded;
    private String oldest_published_date;
    private String newest_published_date;
    private String updated;
}
