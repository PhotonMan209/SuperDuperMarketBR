package com.bramacher.brockhausApplicationProject.service.database;

import com.bramacher.brockhausApplicationProject.model.entity.EntityServiceInterface;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductEntity;
import com.bramacher.brockhausApplicationProject.repository.h2.ProductRepository;
import com.bramacher.brockhausApplicationProject.repository.h2.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements EntityServiceInterface<ProductEntity> {
    //Services with the EntityName are mainly for database actions. More complex logic should be in other services

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    public ProductService(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public void readAndSaveCsvData(BufferedReader csvReader) {
        List<ProductEntity> products = new ArrayList<>();
        String line;
        try {
            // Skip the header line if needed
            csvReader.readLine(); // Skip header assuming first line is header

            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 8) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                ProductEntity product = new ProductEntity();

                // Trim and remove quotes from each relevant field
                product.setName(data[0].trim().replaceAll("\"", ""));
                product.setProductTypeEntity(
                        productTypeRepository.findProductTypeEntityByName(data[1].trim().replaceAll("\"", ""))
                );
                product.setQualityInPercent(Integer.parseInt(data[2].trim().replaceAll("\"", "")));
                product.setMinimumQualityInPercent(Integer.parseInt(data[3].trim().replaceAll("\"", "")));
                String markedForRemovalStr = data[4].trim().replaceAll("\"", "");
                boolean markedForRemoval = !markedForRemovalStr.equals("0");
                product.setMarkedForRemoval(markedForRemoval);

                product.setExpireDate(LocalDate.parse(data[5].trim().replaceAll("\"", "")));
                product.setBasePrice(Double.parseDouble(data[6].trim().replaceAll("\"", "")));
                product.setLastModifiedManual(LocalDate.parse(data[7].trim().replaceAll("\"", "")));
                product.setLastModifiedAutomatic(LocalDate.parse(data[8].trim().replaceAll("\"", "")));

                products.add(product);
            }

            productRepository.saveAll(products);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Repositories should only be accessed by the entity service layer
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
}
