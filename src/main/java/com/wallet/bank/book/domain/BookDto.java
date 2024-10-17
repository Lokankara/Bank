package com.wallet.bank.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private Integer year;
    private Integer quantity;
    private List<String> authors;
    private List<String> genres;
}
