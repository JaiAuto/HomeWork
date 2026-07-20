/*
SQL Task 2 — New Products
Write a SQL query to find products that are new today, meaning they exist in
products_today but not in products_yesterday.
Expected output columns:
product_id, product_name, price, status */


-- Query
SELECT a.product_id,a.product_name,a.price, a.status from products_today as a LEFT JOIN products_yesterday as b on a.product_id=b.product_id where b.product_id IS NULL;