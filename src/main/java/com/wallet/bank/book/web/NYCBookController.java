package com.wallet.bank.book.web;

import com.wallet.bank.book.service.MonoBookService;
import com.wallet.bank.book.dto.BookResponse;
import com.wallet.bank.book.dto.CurrentBooksResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class NYCBookController {

    private final MonoBookService monoBookService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<? extends BookResponse> fetchBookLists(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String title) {
        return monoBookService.fetchBookLists(name, title);
    }

    @GetMapping(value = "/{listNameEncoded}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CurrentBooksResponse> getCurrentBooks(@PathVariable String listNameEncoded) {
        return monoBookService.fetchCurrentBooks(listNameEncoded);
    }
}
