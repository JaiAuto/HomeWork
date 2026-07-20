package fileComparision;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class csvFileReader {

    public static List<String> readHeaders(String filePath) {

        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        File file = new File(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("Invalid file: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String headerLine = reader.readLine();

            if (headerLine == null) {
                throw new IllegalArgumentException("CSV file is empty.");
            }

            if (headerLine.trim().isEmpty()) {
                throw new IllegalArgumentException("Header row is empty.");
            }

            String[] headers = headerLine.split(",");

            List<String> headerList = new ArrayList<>();
            Set<String> uniqueHeaders = new HashSet<>();

            for (String header : headers) {

                header = header.trim();

                if (!header.isEmpty()) {

                    if (!uniqueHeaders.add(header)) {
                        throw new IllegalArgumentException("Duplicate header found: " + header);
                    }

                    headerList.add(header);
                }
            }

            if (headerList.isEmpty()) {
                throw new IllegalArgumentException("No valid headers found.");
            }

            return headerList;

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }
}