# AI Transcript - SDET Take-Home Exercise

## AI Tool Used
**ChatGPT (OpenAI)**

## Initial Consultation Session

### Prompt 1: Project Structure Guidance
**User:** "I need to create a Java Maven project for CSV file comparison. What should be the basic structure and dependencies?"

**ChatGPT Response:** Suggested Maven project structure with:
- Standard src/main/java and src/test/java directories
- Dependencies for OpenCSV, TestNG, and RestAssured
- Basic pom.xml configuration

**Used:** Project structure layout and dependency suggestions

---

### Prompt 2: CSV Header Comparison Logic
**User:** "How to read first line of CSV, split headers, and compare two lists in Java?"

**ChatGPT Response:** Provided approach using:
- BufferedReader to read first line
- String.split(",") for CSV parsing
- ArrayList operations for list comparisons
- Basic error handling patterns

**Used:** Core logic structure for header reading and comparison

---

### Prompt 3: Error Handling Best Practices
**User:** "What error handling should I add for file operations and CSV parsing?"

**ChatGPT Response:** Suggested handling:
- File not found exceptions
- Empty file scenarios
- Invalid CSV format cases
- Null pointer checks
- Try-with-resources pattern

**Used:** Error handling patterns and exception management approach

---

### Prompt 4: TestNG Test Structure
**User:** "How to structure TestNG tests for file comparison functionality?"

**ChatGPT Response:** Provided:
- @Test annotation usage
- Test method naming conventions
- Assert statement patterns
- Test data organization suggestions

**Used:** Basic test structure and assertion patterns

---

### Prompt 5: SQL Query Optimization
**User:** "Best practices for JOIN operations in product comparison queries?"

**ChatGPT Response:** Explained:
- INNER JOIN vs LEFT JOIN use cases
- When to use EXISTS vs JOIN
- Performance considerations
- NULL handling in comparisons

**Used:** SQL approach explanations and JOIN strategy guidance

---

## Manual Implementation and Modifications

### What I Changed/Implemented Myself:

1. **Complete Business Logic**
   - All CSV comparison algorithms written from scratch
   - Custom header trimming and validation logic
   - Specific error messages and handling flows

2. **File Structure Organization**
   - Organized packages and class relationships
   - Created comprehensive test data sets
   - Designed modular class separation

3. **SQL Queries**
   - All SQL queries written independently based on requirements
   - Customized for specific product comparison scenarios
   - Added detailed comments and explanations

4. **Test Implementation**
   - Designed specific test scenarios for edge cases
   - Created custom test CSV files with various conditions
   - Implemented comprehensive error condition testing

5. **API Test Design**
   - RestAssured implementation written independently
   - Test case selection and validation logic
   - Mock endpoint configuration

6. **Documentation**
   - All README content written manually
   - Technical explanations and assumptions documented independently
   - Usage instructions based on actual testing

## Code Originality Statement

**Percentage Breakdown:**
- **AI-assisted:** ~20% (structural guidance, best practices)
- **Manual implementation:** ~80% (business logic, testing, customization)

The AI was primarily used for:
- Initial project structure guidance
- Best practice recommendations
- Error handling patterns

All core functionality, business logic, SQL queries, tests, and documentation were implemented manually based on the specific requirements of this exercise.

## Development Approach

1. Used ChatGPT for initial architectural guidance
2. Implemented all functionality manually
3. Tested and debugged independently
4. Customized solutions to meet exact requirements
5. Validated all deliverables through manual testing

The final implementation represents original work built upon foundational guidance from ChatGPT.