/* 
SQL Task 4 — Status Changes
Write a SQL query to find products whose status changed from yesterday to today.
Expected output columns:
product_id, product_name, old_status, new_status
*/

--QUERY

SELECT a.product_id,a.product_name,a.status as old_status,b.status as new_status FROM products_yesterday as a inner join products_today as b on a.product_id=b.product_id where a.status!=b.status;