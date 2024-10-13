-- Insert sample data into Customers table
INSERT INTO Customers (name, email, image_url)
VALUES ('Wonder Woman', 'wonder.woman@dccomics.com', 'https://pngimg.com/uploads/wonder_woman/wonder_woman_PNG42.png'),
       ('Red Hood', 'red.hood@dccomics.com', 'https://i.pinimg.com/originals/5f/f4/7c/5ff47cb34ad037fb7f850045039ad3e3.png'),
       ('Mandalorian', 'mandalorian@dccomics.com', 'https://pngimg.com/uploads/mandalorian/mandalorian_PNG13.png'),
       ('Joker', 'joker@dccomics.com', 'https://pngimg.com/uploads/joker/joker_PNG49.png'),
       ('Iron Man', 'iron.man@marvel.com', 'https://pngimg.com/uploads/ironman/ironman_PNG88.png'),
       ('Robin', 'Robin@dccomics.com', 'https://pngimg.com/uploads/batman/batman_PNG104.png'),
       ('Batman', 'batman2@dccomics.com', 'https://pngimg.com/uploads/batman/batman_PNG105.png'),
       ('Thor', 'thor@marvel.com', 'http://vignette1.wikia.nocookie.net/disney/images/4/44/AoU_Thor_02.png'),
       ('Spider-Man', 'spider.man@marvel.com', 'https://pngimg.com/uploads/spider_man/spider_man_PNG15.png'),
       ('Captain America', 'captain.america1@marvel.com', 'https://pngimg.com/uploads/captain_america/captain_america_PNG37.png'),
       ('Deadpool', 'deadpool@marvel.com', 'https://pngimg.com/uploads/deadpool/deadpool_PNG76.png'),
       ('Hulk', 'hulk@marvel.com', 'https://pngimg.com/uploads/hulk/hulk_PNG65.png'),
       ('Super man', 'superman@dccomics.com', 'https://pngimg.com/uploads/superman/superman_PNG35.png'),
       ('Super girl', 'supergirl@dccomics.com', 'https://pngimg.com/uploads/supergirl/supergirl_PNG12.png'),
       ('Venom', 'venom@marvel.com', 'https://pngimg.com/uploads/venom/venom_PNG22.png'),
       ('Doctor Strange', 'strange@marvel.com', 'https://www.pngall.com/wp-content/uploads/11/Doctor-Strange-Transparent.png'),
       ('Aqua Man', 'aqua@marvel.com', 'https://www.vhv.rs/file/max/30/306708_aquaman-png.png'),
       ('Harley Quinn', 'harley.quinn@dccomics.com', 'https://pngimg.com/uploads/harley_quinn/harley_quinn_PNG19.png');

-- Insert sample data into Accounts table
INSERT INTO Accounts (customer_id, account_type, balance)
VALUES (1, 'Checking', 5000.00),
       (1, 'Savings', 15000.00),
       (2, 'Checking', 8000.00),
       (3, 'Savings', 12000.00),
       (4, 'Checking', 3000.00),
       (5, 'Savings', 4000.00);

-- Insert sample data into Transactions table
INSERT INTO Transactions (account_id, transaction_date, amount, transaction_type)
VALUES (1, '2024-01-15 10:00:00', 200.00, 'Deposit'),
       (1, '2024-01-20 11:30:00', 50.00, 'Withdrawal'),
       (2, '2024-01-16 14:00:00', 500.00, 'Deposit'),
       (3, '2024-01-18 09:00:00', 1000.00, 'Deposit'),
       (4, '2024-01-19 15:30:00', 150.00, 'Withdrawal'),
       (5, '2024-01-21 12:45:00', 700.00, 'Deposit'),
       (1, '2024-01-22 16:00:00', 300.00, 'Deposit'),
       (2, '2024-01-23 13:00:00', 200.00, 'Withdrawal'),
       (3, '2024-01-24 08:30:00', 400.00, 'Deposit');
