package fileComparision;

import java.util.ArrayList;
import java.util.List;

public class CompareHeaders {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: java fileComparision.CompareHeaders <expected_csv> <actual_csv>");
            return;
        }

        String expectedFile = args[0];
        String actualFile = args[1];

        List<String> expectedHeaders = csvFileReader.readHeaders(expectedFile);
        List<String> actualHeaders = csvFileReader.readHeaders(actualFile);

        System.out.println("Expected Headers : " + expectedHeaders);
        System.out.println("Actual Headers   : " + actualHeaders);

        List<String> commonHeaders = getCommonHeaders(expectedHeaders, actualHeaders);
        List<String> expectedOnlyHeaders = getExpectedOnlyHeaders(expectedHeaders, actualHeaders);
        List<String> actualOnlyHeaders = getActualOnlyHeaders(expectedHeaders, actualHeaders);

        System.out.println("\nCommon Headers:");
        commonHeaders.forEach(System.out::println);

        System.out.println("\nHeaders only in Expected CSV:");
        expectedOnlyHeaders.forEach(System.out::println);

        System.out.println("\nHeaders only in Actual CSV:");
        actualOnlyHeaders.forEach(System.out::println);
    }

    public static List<String> getCommonHeaders(List<String> expected, List<String> actual) {

        List<String> commonHeaders = new ArrayList<>();

        for (String header : expected) {
            if (actual.contains(header)) {
                commonHeaders.add(header);
            }
        }

        return commonHeaders;
    }

    public static List<String> getExpectedOnlyHeaders(List<String> expected, List<String> actual) {

        List<String> expectedOnlyHeaders = new ArrayList<>();

        for (String header : expected) {
            if (!actual.contains(header)) {
                expectedOnlyHeaders.add(header);
            }
        }

        return expectedOnlyHeaders;
    }

    public static List<String> getActualOnlyHeaders(List<String> expected, List<String> actual) {

        List<String> actualOnlyHeaders = new ArrayList<>();

        for (String header : actual) {
            if (!expected.contains(header)) {
                actualOnlyHeaders.add(header);
            }
        }

        return actualOnlyHeaders;
    }
}