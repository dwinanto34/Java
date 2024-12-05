package com.learning.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class CsvApp {
    private static void createCsv() throws IOException {
        StringWriter stringWriter = new StringWriter();

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("First Name", "Last Name", "Age")
                .build();

        CSVPrinter printer = new CSVPrinter(stringWriter, format);
        printer.printRecord("John", "Christian", 29);
        printer.printRecord("Mary", "Kristen", 28);

        printer.flush();

        String csv = stringWriter.getBuffer().toString();
        System.out.println("Writing file..");
        System.out.println(csv);

        try (FileWriter fileWriter = new FileWriter("./src/main/resources/students.csv")) {
            fileWriter.write(csv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readCsv() throws IOException {
        Path path = Path.of("./src/main/resources/students.csv");
        Reader reader = Files.newBufferedReader(path);

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();
        CSVParser parser = new CSVParser(reader, format);
        Map<String, Integer> headerMap = parser.getHeaderMap();

        System.out.println("Reading file..");

        String header = String.join(", ", headerMap.keySet());
        System.out.println(header);
        for (CSVRecord record : parser) {
            StringBuilder sb = new StringBuilder()
                    .append(record.get("First Name"))
                    .append(", ")
                    .append(record.get("Last Name"))
                    .append(", ")
                    .append(record.get("Age"));
            System.out.println(sb);
        }
    }

    private static void removeCsv() throws IOException {
        System.out.println("Deleting file..");
        Files.delete(Path.of("./src/main/resources/students.csv"));
    }

    public static void run() throws IOException {
        createCsv();
        readCsv();
        removeCsv();
    }
}
