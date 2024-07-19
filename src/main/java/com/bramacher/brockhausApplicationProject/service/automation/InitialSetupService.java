package com.bramacher.brockhausApplicationProject.service.automation;

import com.bramacher.brockhausApplicationProject.service.database.ProductService;
import com.bramacher.brockhausApplicationProject.service.database.ProductTypeService;
import com.bramacher.brockhausApplicationProject.service.database.QualityDegradationService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class InitialSetupService {

    private final ProductService productService;
    private final ProductTypeService productTypeService;
    private final QualityDegradationService qualityDegradationService;

    public InitialSetupService(ProductService productService, ProductTypeService productTypeService, QualityDegradationService qualityDegradationService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
        this.qualityDegradationService = qualityDegradationService;
    }

    @PostConstruct
    public void readInInitialCsvData() {
        loadCsvData("/csv/InitialProductTypes.csv", productTypeService::readAndSaveCsvData);
        loadCsvData("/csv/InitialProducts.csv", productService::readAndSaveCsvData);
        loadCsvData("/csv/InitialQualityDegradations.csv", qualityDegradationService::readAndSaveCsvData);
    }

    private void loadCsvData(String path, CsvDataConsumer consumer) {
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + path);
            }
            System.out.println("Loading resource: " + path);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                consumer.readAndSaveCsvData(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    private interface CsvDataConsumer {
        void readAndSaveCsvData(BufferedReader reader) throws IOException;
    }
}