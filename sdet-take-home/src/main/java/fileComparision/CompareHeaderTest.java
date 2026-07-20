package fileComparision;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class CompareHeaderTest {

    @Test
    public void testIdenticalHeaders() {

        List<String> expected = csvFileReader.readHeaders(
                "src/test/resources/common_headers_expected.csv");

        List<String> actual = csvFileReader.readHeaders(
                "src/test/resources/common_headers_actual.csv");

        assertEquals(CompareHeaders.getCommonHeaders(expected, actual), expected);
        assertEquals(CompareHeaders.getExpectedOnlyHeaders(expected, actual).size(), 0);
        assertEquals(CompareHeaders.getActualOnlyHeaders(expected, actual).size(), 0);
    }

    @Test
    public void testMissingHeader() {

        List<String> expected = csvFileReader.readHeaders(
                "src/test/resources/missing_header_expected.csv");

        List<String> actual = csvFileReader.readHeaders(
                "src/test/resources/missing_header_actual.csv");

        assertEquals(
                CompareHeaders.getExpectedOnlyHeaders(expected, actual),
                Arrays.asList("amount"));
    }

    @Test
    public void testHeadersWithExtraSpaces() {

        List<String> expected = csvFileReader.readHeaders(
                "src/test/resources/spaces_expected.csv");

        List<String> actual = csvFileReader.readHeaders(
                "src/test/resources/spaces_actual.csv");

        assertEquals(
                CompareHeaders.getCommonHeaders(expected, actual),
                expected);
    }
    
    @Test
    public void testFileDoesNotExist() {
        csvFileReader.readHeaders("src/test/resources/file_not_found.csv");
    }
    
    @Test
    public void testHeadersInDifferentOrder() {

        List<String> expected =
                csvFileReader.readHeaders("src/test/resources/header_order_expected.csv");

        List<String> actual =
                csvFileReader.readHeaders("src/test/resources/header_order_actual.csv");

        assertEquals(
                CompareHeaders.getCommonHeaders(expected, actual),
                expected);
    }
}