SELECT books.name, authors.name, genres.name
FROM books
         join books_authors on books.id = books_authors.book_id
         join public.authors on public.authors.id = books_authors.author_id
         join books_genres on books.id = books_genres.book_id
         join genres on genres.id = books_genres.genre_id;

SELECT books.name, authors.name, genres.name
from books
         join books_authors on books_authors.book_id = books.id
         join public.authors on authors.id = books_authors.author_id
         join public.books_genres on books.id = books_genres.genre_id
         join public.genres on genres.id = books_genres.genre_id;

SELECT books.name, subscribers.id, subscribers.name, subscriptions.start, subscriptions.finish
from books
         join subscriptions on books.id = subscriptions.b_id
         join subscribers on subscriptions.s_id = subscribers.id;

Select distinct books.id
from books
         join subscriptions on subscriptions.id = books.id;


SELECT books.name, count(public.subscriptions.id) as times
from books
         join subscriptions on books.id = subscriptions.b_id
GROUP BY books.name;

SELECT count(subscribers.id)
from subscribers;

SELECT count(distinct subscriptions) as use
from subscriptions
where subscriptions.is_active = 'Y';

SELECT count(distinct subscriptions.id) as total
from subscriptions
WHERE is_active = true;

SELECT SUM(books.quantity) as sum,
       MIN(books.quantity) as min,
       MAX(books.quantity) as max,
       avg(books.quantity) as avg
from books;

SELECT MAX(subscriptions.start) as first,
       MIN(subscriptions.start) as last
from public.books
         join subscriptions on subscriptions.b_id = books.id
GROUP BY books.id, books.name;

SELECT books.name, books.year
from books
order by year DESC;

SELECT distinct authors.name
from authors
         join books_authors on authors.id = books_authors.author_id
         join subscriptions on subscriptions.is_active
WHERE subscriptions.is_active
ORDER BY name DESC;

SELECT id, name, year, quantity
FROM books
WHERE year BETWEEN 2005 and 2010
  AND quantity > 4;

SELECT id, s_id, b_id, start, finish, is_active
FROM subscriptions
where start >= '2023-06-01'
  and start < '2023-09-01';
