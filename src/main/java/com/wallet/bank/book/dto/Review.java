package com.wallet.bank.book.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Review {
    private String book_title;
    private String book_author;
    private String publication_dt;
    private String byline;
    private String summary;
    private String url;
}
