-- Optional: Analyze the indexes to gather statistics
ANALYZE Customers;
ANALYZE Accounts;
ANALYZE Transactions;
-- Test the Single-Column Index on Email
EXPLAIN ANALYZE
SELECT * FROM Customers WHERE email = 'clark.kent@dccomics.com';

-- Test the Unique Index on Customer Name
EXPLAIN ANALYZE
SELECT * FROM Customers WHERE customer_name = 'Bruce Wayne';

-- Test the Composite Index on Account ID and Transaction Date
EXPLAIN ANALYZE
SELECT * FROM Transactions
WHERE account_id = 1 AND transaction_date BETWEEN '2024-01-15' AND '2024-01-30';

-- Test the Full-Text Index
EXPLAIN ANALYZE
SELECT * FROM Customers
WHERE to_tsvector('english', customer_name) @@ to_tsquery('bruce:*');

-- Check All Transactions for a Specific Account
EXPLAIN ANALYZE
SELECT * FROM Transactions WHERE account_id = 2;