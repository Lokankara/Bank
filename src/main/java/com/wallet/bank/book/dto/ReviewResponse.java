package com.wallet.bank.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewResponse extends BookResponse {

    @JsonProperty("results")
    private List<Review> results;
}
