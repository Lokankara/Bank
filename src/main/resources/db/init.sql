DROP TABLE IF EXISTS Transactions CASCADE;
DROP TABLE IF EXISTS Accounts CASCADE;
DROP TABLE IF EXISTS Customers CASCADE;

-- Create Customers Table
CREATE TABLE Customers
(
    customer_id SERIAL PRIMARY KEY,
    name        VARCHAR(100),
    image_url   VARCHAR(100),
    email       VARCHAR(100) UNIQUE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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
