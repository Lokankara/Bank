package com.wallet.bank.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BookListResponse extends BookResponse {
    @JsonProperty("results")
    private List<BookList> results;
}
