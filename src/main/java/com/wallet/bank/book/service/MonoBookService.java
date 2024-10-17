package com.wallet.bank.book.service;

import com.wallet.bank.book.dto.BookListResponse;
import com.wallet.bank.book.dto.BookResponse;
import com.wallet.bank.book.dto.CurrentBooksResponse;
import com.wallet.bank.book.dto.ReviewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MonoBookService {
    private final WebClient webClient;

    @Value("${nyt.api.key}")
    private String apiKey;

    public MonoBookService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.nytimes.com/svc/books/v3").build();
    }

    public Mono<? extends BookResponse> fetchBookLists(String name, String title) {
        if (name == null && title == null) {
            return webClient.get()
                    .uri("/lists/names.json?api-key={apiKey}", apiKey)
                    .retrieve()
                    .bodyToMono(BookListResponse.class);
        } else if (name != null) {
            return webClient.get()
                    .uri("/reviews.json?author={name}&api-key={apiKey}", name, apiKey)
                    .retrieve()
                    .bodyToMono(ReviewResponse.class);
        } else {
            return webClient.get()
                    .uri("/reviews.json?title={title}&api-key={apiKey}", title, apiKey)
                    .retrieve()
                    .bodyToMono(ReviewResponse.class);
        }
    }

    public Mono<CurrentBooksResponse> fetchCurrentBooks(String listNameEncoded) {
        return webClient.get()
                .uri("/lists/current/{listNameEncoded}.json?api-key={apiKey}", listNameEncoded, apiKey)
                .retrieve()
                .bodyToMono(CurrentBooksResponse.class);
    }
}
