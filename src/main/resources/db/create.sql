DROP TABLE IF EXISTS books_authors CASCADE;
DROP TABLE IF EXISTS books_genres CASCADE;
DROP TABLE IF EXISTS subscriptions CASCADE;
DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS subscribers CASCADE;

CREATE TABLE authors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE genres
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE books
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    year     INT          NOT NULL,
    quantity INT          NOT NULL
);

CREATE TABLE subscribers
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE books_authors
(
    book_id   INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);

CREATE TABLE books_genres
(
    book_id  INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE
);

CREATE TABLE subscriptions
(
    id        SERIAL PRIMARY KEY,
    s_id      INT     NOT NULL,
    b_id      INT     NOT NULL,
    start     DATE    NOT NULL,
    finish    DATE    NOT NULL,
    is_active BOOLEAN NOT NULL,
    FOREIGN KEY (s_id) REFERENCES subscribers (id) ON DELETE CASCADE,
    FOREIGN KEY (b_id) REFERENCES books (id) ON DELETE CASCADE
);
