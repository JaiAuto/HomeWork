-- SDET Take-Home Exercise - SQL Answers
-- All SQL queries consolidated in one file

-- ============================================================================
-- SQL Task 1 — Price Changes
-- Find products whose price changed from yesterday to today
-- Expected output columns: product_id, product_name, old_price, new_price
-- ============================================================================

SELECT a.product_id,
       a.product_name,
       a.price as old_price,
       b.price as new_price
FROM products_yesterday as a
INNER JOIN products_today as b ON a.product_id = b.product_id
WHERE a.price != b.price;

-- ============================================================================
-- SQL Task 2 — New Products
-- Find products that are new today (exist in products_today but not in products_yesterday)
-- Expected output columns: product_id, product_name, price, status
-- ============================================================================

SELECT a.product_id,
       a.product_name,
       a.price,
       a.status
FROM products_today as a
LEFT JOIN products_yesterday as b ON a.product_id = b.product_id
WHERE b.product_id IS NULL;

-- ============================================================================
-- SQL Task 3 — Missing Products
-- Find products that existed yesterday but are missing today
-- Expected output columns: product_id, product_name, price, status
-- ============================================================================

SELECT a.product_id,
       a.product_name,
       a.price,
       a.status
FROM products_yesterday as a
LEFT JOIN products_today as b ON a.product_id = b.product_id
WHERE b.product_id IS NULL;

-- ============================================================================
-- SQL Task 4 — Status Changes
-- Find products whose status changed from yesterday to today
-- Expected output columns: product_id, product_name, old_status, new_status
-- ============================================================================

SELECT a.product_id,
       a.product_name,
       a.status as old_status,
       b.status as new_status
FROM products_yesterday as a
INNER JOIN products_today as b ON a.product_id = b.product_id
WHERE a.status != b.status;

-- ============================================================================
-- Sample Data Sets for Testing
-- ============================================================================

/*
Table: products_yesterday
product_id | product_name  | price | status
1001       | Coffee Mug    | 12.50 | ACTIVE
1002       | Laptop Stand  | 38.00 | ACTIVE
1003       | Wireless Mouse| 19.99 | ACTIVE
1004       | Old Keyboard  | 29.99 | DISCONTINUED
1005       | Notebook      | 4.50  | ACTIVE
1008       | Desk Lamp     | 24.00 | ACTIVE

Table: products_today
product_id | product_name  | price | status
1001       | Coffee Mug    | 12.50 | ACTIVE
1002       | Laptop Stand  | 35.00 | ACTIVE
1003       | Wireless Mouse| 21.99 | ACTIVE
1005       | Notebook      | 4.50  | INACTIVE
1006       | Webcam        | 59.00 | ACTIVE
1007       | USB Cable     | 7.99  | ACTIVE
1008       | Desk Lamp     | 24.00 | ACTIVE

Expected Results:

Task 1 (Price Changes):
1002 | Laptop Stand  | 38.00 | 35.00
1003 | Wireless Mouse| 19.99 | 21.99

Task 2 (New Products):
1006 | Webcam    | 59.00 | ACTIVE
1007 | USB Cable | 7.99  | ACTIVE

Task 3 (Missing Products):
1004 | Old Keyboard | 29.99 | DISCONTINUED

Task 4 (Status Changes):
1005 | Notebook | ACTIVE | INACTIVE
*/