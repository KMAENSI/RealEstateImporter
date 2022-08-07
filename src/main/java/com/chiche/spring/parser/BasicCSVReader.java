package com.chiche.spring.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by rajeevkumarsingh on 25/09/17.
 */
public class BasicCSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "./users.csv";
   // private static final String SAMPLE_CSV_FILE_PATH = "./input_real_estates.csv";

    public static void main(String[] args) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index

                String commune = csvRecord.get(0);


                String nombreVente = csvRecord.get(1);
                String prixMoyen = csvRecord.get(2);
                String fourchetteM2 = csvRecord.get(3);
                String nombreVefa = csvRecord.get(4);
                String prixM2Vefa = csvRecord.get(5);
                String fourchetteM2Vefa = csvRecord.get(6);

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Commune  : " + commune);
                System.out.println("Nombre de vente : " + nombreVente);
                System.out.println("Prix moyen : " + prixMoyen);
                System.out.println("fourchetteM2  : " + fourchetteM2);
                System.out.println("nombreVefa  : " + nombreVefa);
                System.out.println("prixM2Vefa  : " + prixM2Vefa);
                System.out.println("fourchetteM2Vefa  : " + fourchetteM2Vefa);
                System.out.println("---------------\n\n");
            }
        }
    }
}
