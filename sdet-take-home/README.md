# SDET Take-Home Exercise

## Language Used
Java with Maven build automation tool

## How to Run the CSV Comparison Tool

### Prerequisites
- Java 8 or higher

### Compilation and Execution (Verified Working Method)

#### Step 1: Navigate to source directory
```bash
cd sdet-take-home/src/main/java/fileComparision
```

#### Step 2: Compile the Java files
```bash
javac CompareHeaders.java csvFileReader.java
```

#### Step 3: Navigate back to src/main/java directory
```bash
cd ..
```

#### Step 4: Run the comparison tool
```bash
java fileComparision.CompareHeaders "path/to/expected_orders.csv" "path/to/actual_orders.csv"
```

### Example Usage (with full paths)
```bash
# Windows example:
java fileComparision.CompareHeaders "C:\path\to\sdet-take-home\src\main\resources\expected_orders.csv" "C:\path\to\sdet-take-home\src\main\resources\actual_orders.csv"

# Unix/Mac example:
java fileComparision.CompareHeaders "/path/to/sdet-take-home/src/main/resources/expected_orders.csv" "/path/to/sdet-take-home/src/main/resources/actual_orders.csv"
```

### Expected Output
```
Expected Headers : [order_id, customer_id, amount, currency, status, created_at, country]
Actual Headers   : [order_id, customer_id, total_amount, currency, status, processed_at, country_code]

Common Headers:
order_id
customer_id
currency
status

Headers only in Expected CSV:
amount
created_at
country

Headers only in Actual CSV:
total_amount
processed_at
country_code
```

### Alternative: Maven Build (Note: Currently has compilation issues)
```bash
# If Maven build is fixed:
mvn compile
java -cp target/classes fileComparision.CompareHeaders expected_orders.csv actual_orders.csv
```

## How to Run Tests

### Method 1: IDE TestNG Execution (Verified Working)
- Right-click on `CompareHeaderTest.java` in your IDE
- Select "Run as TestNG Test"
- All tests execute successfully with this method

### Method 2: Maven (Alternative)
```bash
# Note: Requires moving CompareHeaderTest.java to src/test/java directory first
mvn test

# Run specific test class
mvn test -Dtest=CompareHeaderTest
```

### Method 3: Direct Java Execution
```bash
# Compile test dependencies first, then run with TestNG runner
java org.testng.TestNG testng.xml
```

## SQL Answers

### Task 5 - SQL Approach Explanations

#### 1. Why did you use INNER JOIN, LEFT JOIN, NOT EXISTS, or another approach?

**Task 1 (Price Changes) - INNER JOIN:**
- Used INNER JOIN because we only want products that exist in BOTH yesterday and today tables
- We need to compare prices, so the product must be present in both datasets
- INNER JOIN ensures we only get matching records where price comparison is possible

**Task 2 (New Products) - LEFT JOIN:**
- Used LEFT JOIN from products_today to products_yesterday with WHERE b.product_id IS NULL
- This finds products that exist in today's table but not in yesterday's table
- LEFT JOIN keeps all records from the left table (today) and shows NULL for non-matches from right table (yesterday)

**Task 3 (Missing Products) - LEFT JOIN:**
- Used LEFT JOIN from products_yesterday to products_today with WHERE b.product_id IS NULL
- This finds products that existed yesterday but are missing today
- Similar logic to Task 2 but reversed - checking what's in yesterday but not in today

**Task 4 (Status Changes) - INNER JOIN:**
- Used INNER JOIN because we need products that exist in BOTH tables to compare status
- Only products present in both datasets can have status changes
- INNER JOIN ensures we have valid status values from both tables for comparison

#### 2. What would change if product_id was not unique?

If product_id was not unique, several issues would arise:

- **Duplicate Results:** Queries would return multiple rows for the same logical product
- **Incorrect Comparisons:** A product from yesterday might match multiple products today, leading to false price/status change detection
- **Data Integrity Issues:** Business logic would break as we couldn't reliably identify individual products

**Solutions if product_id was not unique:**
- Use composite keys (e.g., product_id + version + timestamp)
- Add DISTINCT clauses to remove duplicates
- Use window functions with ROW_NUMBER() to get latest version of each product
- Group by product attributes and use aggregate functions (MAX, MIN) as appropriate

#### 3. What issues could happen if price or status can be NULL?

**Price NULL Issues:**
- **Comparison Failures:** `a.price != b.price` would not match NULL values correctly (NULL != NULL is NULL/false in SQL)
- **Missing Changes:** Price changes from/to NULL wouldn't be detected
- **Arithmetic Errors:** Calculations involving NULL prices would result in NULL

**Status NULL Issues:**
- **Incomplete Change Detection:** Status changes involving NULL values might be missed
- **Business Logic Errors:** Applications might not handle NULL status properly
- **Reporting Inaccuracies:** NULL status could be interpreted differently by different systems

**Solutions for NULL handling:**
```sql
-- For price comparisons, use COALESCE or IS NULL checks:
WHERE COALESCE(a.price, -1) != COALESCE(b.price, -1)
OR (a.price IS NULL AND b.price IS NOT NULL)
OR (a.price IS NOT NULL AND b.price IS NULL)

-- For status comparisons:
WHERE COALESCE(a.status, 'UNKNOWN') != COALESCE(b.status, 'UNKNOWN')
```

## API Test Cases

### Test Case 1: Valid Order ID Returns Success
- **Input:** GET /api/orders/ORD-1001
- **Expected:** HTTP 200, status = "PAID"
- **Why Useful:** Validates happy path for existing paid orders

### Test Case 2: Invalid Order ID Returns Not Found
- **Input:** GET /api/orders/INVALID-ID
- **Expected:** HTTP 404
- **Why Useful:** Ensures proper error handling for non-existent orders

### Test Case 3: Malformed Order ID Returns Bad Request
- **Input:** GET /api/orders/123 (without ORD prefix)
- **Expected:** HTTP 400
- **Why Useful:** Validates input format validation

### Test Case 4: Response Schema Validation
- **Input:** GET /api/orders/ORD-1001
- **Expected:** HTTP 200 with all required fields (order_id, customer_id, amount, currency, status, created_at)
- **Why Useful:** Ensures API contract compliance

### Test Case 5: Different Order Status Validation
- **Input:** GET /api/orders/ORD-1002 (pending order)
- **Expected:** HTTP 200, status = "PENDING"
- **Why Useful:** Validates different order states are handled correctly

## Assumptions Made

1. **CSV Format:** Files use comma-separated values with headers in first row
2. **Character Encoding:** UTF-8 encoding assumed for all text files
3. **Product IDs:** Assumed to be unique identifiers in each table
4. **API Endpoint:** Used mock endpoint https://api.example.com for testing purposes
5. **Test Framework:** Used TestNG for unit testing as specified in requirements
6. **Error Handling:** Implemented comprehensive error handling for file operations
7. **Header Trimming:** Automatically trim whitespace from CSV headers as specified

## AI Usage Statement

This exercise was completed with assistance from ChatGPT (OpenAI) for:
- Code structure and best practices guidance
- SQL query optimization suggestions
- Error handling implementation patterns
- Test case design recommendations
- Maven project setup guidance

All final code implementation, logic design, and testing was reviewed and validated by the developer. The majority of the code (~80%) was written manually with AI providing foundational guidance and best practices.
