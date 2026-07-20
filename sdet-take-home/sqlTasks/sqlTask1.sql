/*
SQL Task 1 — Price Changes
Write a SQL query to find products whose price changed from yesterday to today.
Expected output columns:
product_id, product_name, old_price, new_price */


-- Query

SELECT a.product_id,a.product_name,a.price as old_price, b.price as latest_price from products_yesterday as a inner join products_today as b on a.product_id = b.product_id where a.price!=b.price;