package com.chiche.spring.jpa.postgresql.controller;

import com.chiche.spring.jpa.postgresql.model.Commune;
import com.chiche.spring.jpa.postgresql.model.SalesData;
import com.chiche.spring.jpa.postgresql.repository.CommuneRepository;
import com.chiche.spring.jpa.postgresql.repository.SalesDataRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CommuneController {

    @Autowired
    CommuneRepository communeRepository;
    @Autowired
    SalesDataRepository salesDataRepository;

    private static final String SAMPLE_CSV_FILE_PATH = "./users.csv";

    @GetMapping("/communes")
    public ResponseEntity<List<Commune>> getAllCommunes(@RequestParam(required = false) String description) {
        try {
            List<Commune> communes = new ArrayList<>();
            System.out.println("show me ------- ");
            if (description == null) {
                communeRepository.findAll().forEach(communes::add);
            } else
                communeRepository.findByDescriptionContaining(description).forEach(communes::add);

            if (communes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(communes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update_communes")
    public ResponseEntity<Commune> createOrUpdateCommune() {
        try {

            try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH)); CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("communeName", "nombreVente", "prixM2", "FourchetteM2", "nombreVefa", "prixM2Vefa", "fourchetteM2Vefa").withIgnoreHeaderCase().withTrim())) {

                for (CSVRecord csvRecord : csvParser) {
                    String communeName = csvRecord.get("communeName");
                    System.out.println("commune name " + communeName);

                    if (communeRepository.findByDescriptionContaining(communeName).isEmpty()) {
                        // Accessing values by the names assigned to each column
                        Commune commune = communeRepository.save(new Commune(communeName));
                    } else {
                        System.out.println("commune existante " + communeName);
                    }

                }
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update_metrics")
    public ResponseEntity<Commune> updateMertrics() {
        try {

            try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH)); CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("communeName", "nombreVente", "prixM2", "FourchetteM2", "nombreVefa", "prixM2Vefa", "fourchetteM2Vefa").withIgnoreHeaderCase().withTrim())) {

                for (CSVRecord csvRecord : csvParser) {
                    String communeName = csvRecord.get("communeName");
                    // get Commune
                    Commune commune = communeRepository.findByDescriptionContaining(communeName).size() == 1 ? communeRepository.findByDescriptionContaining(communeName).get(0) : null;
                    int nombreVente = Integer.parseInt(csvRecord.get("nombreVente").replaceAll("[^\\d.]", ""));
                    int prixM2 = csvRecord.get("prixM2").contains("*") ? 0 : Integer.parseInt(csvRecord.get("prixM2").replaceAll("[^\\d.]", ""));
                    String fourchetteM2 = csvRecord.get("FourchetteM2").contains("*") ? "" : csvRecord.get("FourchetteM2");
                    int nombreVefa = Integer.parseInt(csvRecord.get("nombreVefa").replaceAll("[^\\d.]", ""));
                    if (commune != null) {
                        System.out.println("commune est  : " + commune.getDescription());
                        // Accessing values by the names assigned to each column
                        SalesData salesData = new SalesData();

                        salesData.setCommune(commune);
                        salesData.setNombreVefa(nombreVefa);
                        salesData.setNombreVente(nombreVente);
                        salesData.setPrixM2(prixM2);
                        salesDataRepository.save(salesData);
                    } else {
                        System.out.println("commune non existante " + communeName);
                    }

                }
            }


            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
