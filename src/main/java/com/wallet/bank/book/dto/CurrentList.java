package com.wallet.bank.book.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CurrentList {
    private String list_name;
    private String list_name_encoded;
    private String bestsellers_date;
    private String published_date;
    private String published_date_description;
    private String next_published_date;
    private String previous_published_date;
    private String display_name;
    private int normal_list_ends_at;
    private String updated;
    private List<BookRequest> bookRequests;
}