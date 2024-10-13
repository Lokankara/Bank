DROP TABLE IF EXISTS Transactions CASCADE;
DROP TABLE IF EXISTS Accounts CASCADE;
DROP TABLE IF EXISTS Customers CASCADE;

-- Create Customers Table
CREATE TABLE Customers
(
    customer_id   SERIAL PRIMARY KEY,
    customer_name VARCHAR(100),
    email         VARCHAR(100) UNIQUE,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Accounts Table
CREATE TABLE Accounts
(
    account_id   SERIAL PRIMARY KEY,
    customer_id  INT REFERENCES Customers (customer_id) ON DELETE CASCADE,
    account_type VARCHAR(50),
    balance      DECIMAL(10, 2) DEFAULT 0.00,
    created_at   TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
);

-- Create Transactions Table
CREATE TABLE Transactions
(
    transaction_id   SERIAL PRIMARY KEY,
    account_id       INT REFERENCES Accounts (account_id) ON DELETE CASCADE,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount           DECIMAL(10, 2),
    transaction_type VARCHAR(50)
);

-- Create Indexes

-- 1. Single-Column Index on email in Customers table
CREATE INDEX idx_customers_email ON Customers (email);

-- 2. Composite Index on account_id and transaction_date in Transactions table
CREATE INDEX idx_transactions_account_date ON Transactions (account_id, transaction_date);

-- 3. Unique Index on customer_name in Customers table
CREATE UNIQUE INDEX idx_customers_unique_name ON Customers (customer_name);

-- 4. Full-Text Index on customer_name in Customers table (for larger text queries)
CREATE INDEX idx_customers_name_fulltext ON Customers USING gin (to_tsvector('english', customer_name));


-- Insert sample data into Customers table
INSERT INTO Customers (customer_name, email)
VALUES ('Clark Kent', 'clark.kent@dccomics.com'),
       ('Bruce Wayne', 'bruce.wayne@dccomics.com'),
       ('Diana Prince', 'diana.prince@dccomics.com'),
       ('Barry Allen', 'barry.allen@dccomics.com'),
       ('Hal Jordan', 'hal.jordan@dccomics.com');

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
