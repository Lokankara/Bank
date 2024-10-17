package com.wallet.bank.book.dao;

import com.wallet.bank.book.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
