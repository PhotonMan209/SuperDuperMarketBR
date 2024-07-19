package com.bramacher.brockhausApplicationProject.service.database;

import com.bramacher.brockhausApplicationProject.model.entity.EntityServiceInterface;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductTypeEntity;
import com.bramacher.brockhausApplicationProject.repository.h2.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeService implements EntityServiceInterface<ProductTypeEntity> {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public void readAndSaveCsvData(BufferedReader csvReader) {
        List<ProductTypeEntity> productTypes = new ArrayList<>();
        String line;
        try {
            // Skip the header line if needed
            csvReader.readLine(); // Skip header assuming first line is header

            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");

                // Create ProductTypeEntity from CSV data
                ProductTypeEntity productType = new ProductTypeEntity();
                productType.setName(data[0].replaceAll("\"", ""));

                productTypes.add(productType);
            }

            // Save product types to database
            productTypeRepository.saveAll(productTypes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
