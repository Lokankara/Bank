package com.wallet.bank.book.service;

import com.wallet.bank.book.dao.BookRepository;
import com.wallet.bank.book.domain.Author;
import com.wallet.bank.book.domain.Book;
import com.wallet.bank.book.domain.BookDto;
import com.wallet.bank.book.domain.Genre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(this::toDto).toList();
    }

    public BookDto getBookById(Long id) {
        return toDto(Objects.requireNonNull(bookRepository.findById(id).orElse(null)));
    }

    public BookDto createBook(Book book) {
        return toDto(bookRepository.save(book));
    }

    public BookDto updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setName(bookDetails.getName());
            book.setYear(bookDetails.getYear());
            book.setQuantity(bookDetails.getQuantity());
            return toDto(bookRepository.save(book));
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .year(book.getYear())
                .quantity(book.getQuantity())
                .authors(book.getAuthors().stream()
                        .map(Author::getName).collect(Collectors.toList()))
                .genres(book.getGenres().stream()
                        .map(Genre::getName).collect(Collectors.toList()))
                .build();
    }
}
