-- Create Indexes

-- 1. Single-Column Index on email in Customers table
CREATE INDEX idx_customers_email ON Customers (email);

-- 2. Composite Index on account_id and transaction_date in Transactions table
CREATE INDEX idx_transactions_account_date ON Transactions (account_id, transaction_date);

-- 3. Unique Index on customer_name in Customers table
CREATE UNIQUE INDEX idx_customers_unique_name ON Customers (name);

-- 4. Full-Text Index on customer_name in Customers table (for larger text queries)
CREATE INDEX idx_customers_name_fulltext ON Customers USING gin (to_tsvector('english', name));

-- Test the Single-Column Index on Email
EXPLAIN ANALYZE
SELECT * FROM Customers WHERE email = 'clark.kent@dccomics.com';

-- Test the Unique Index on Customer Name
EXPLAIN ANALYZE
SELECT * FROM Customers WHERE name = 'Joker';

-- Test the Composite Index on Account ID and Transaction Date
EXPLAIN ANALYZE
SELECT * FROM Transactions
WHERE account_id = 1 AND transaction_date BETWEEN '2024-01-15' AND '2024-01-30';

-- Test the Full-Text Index
EXPLAIN ANALYZE
SELECT * FROM Customers
WHERE to_tsvector('english', name) @@ to_tsquery('bruce:*');

-- Check All Transactions for a Specific Account
EXPLAIN ANALYZE
SELECT * FROM Transactions WHERE account_id = 2;

-- Optional: Analyze the indexes to gather statistics
ANALYZE Customers;
ANALYZE Accounts;
ANALYZE Transactions;

SELECT *
FROM Customers;
