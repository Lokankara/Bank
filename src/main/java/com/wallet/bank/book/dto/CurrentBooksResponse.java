package com.wallet.bank.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CurrentBooksResponse {
    private String status;
    private String copyright;
    private int num_results;
    private String last_modified;
    private CurrentList results;
}
