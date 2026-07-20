/*
SQL Task 3 — Missing Products
Write a SQL query to find products that existed yesterday but are missing today.
Expected output columns:
product_id, product_name, price, status
*/

--Query

SELECT a.product_id,a.product_name, a.price,a.status FROM products_yesterday as a Left JOIN products_today as b on a.product_id=b.product_id where b.product_id is null;