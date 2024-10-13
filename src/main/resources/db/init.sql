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
