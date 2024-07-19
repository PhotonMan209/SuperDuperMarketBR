package com.bramacher.brockhausApplicationProject.service.database;

import com.bramacher.brockhausApplicationProject.model.entity.EntityServiceInterface;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductEntity;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductTypeEntity;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.QualityDegradationRuleEntity;
import com.bramacher.brockhausApplicationProject.repository.h2.ProductTypeRepository;
import com.bramacher.brockhausApplicationProject.repository.h2.QualityDegradationRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QualityDegradationService implements EntityServiceInterface<QualityDegradationRuleEntity> {

    private final QualityDegradationRepository qualityDegradationRepository;
    private final ProductTypeRepository productTypeRepository;

    public QualityDegradationService(QualityDegradationRepository qualityDegradationRepository, ProductTypeRepository productTypeRepository) {
        this.qualityDegradationRepository = qualityDegradationRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public void readAndSaveCsvData(BufferedReader csvReader) {
        List<QualityDegradationRuleEntity> qualityDegradations = new ArrayList<>();
        String line;
        try {
            // Skip the header line if needed
            csvReader.readLine(); // Skip header assuming first line is header

            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 4) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                QualityDegradationRuleEntity qualityDegradation = new QualityDegradationRuleEntity();

                // Trim and remove quotes from each relevant field
                qualityDegradation.setName(data[0].trim().replaceAll("\"", ""));

                long productTypeId = Long.parseLong(data[1].trim().replaceAll("\"", ""));
                ProductTypeEntity productType = productTypeRepository.findById(productTypeId).orElse(null);
                qualityDegradation.setProductTypeEntity(productType);

                qualityDegradation.setDegradationInPercentPoints(Integer.parseInt(data[2].trim().replaceAll("\"", "")));

                // Enhanced parsing for degradationAfterDays
                String degradationAfterDaysStr = data[3].trim().replaceAll("\"", ""); // Remove quotes
                degradationAfterDaysStr = degradationAfterDaysStr.split("//")[0].trim(); // Remove comment, if present
                qualityDegradation.setDegradationAfterDays(Integer.parseInt(degradationAfterDaysStr));

                qualityDegradations.add(qualityDegradation);
            }
            // Save quality degradations to database
            qualityDegradationRepository.saveAll(qualityDegradations);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Trying to apply a rule if necessary
    public List<ProductEntity> degradeProductByCategory(Map<ProductEntity, QualityDegradationRuleEntity> productMap, int daysPassed) {
        List<ProductEntity> modifiedProducts = new ArrayList<>();

        for (Map.Entry<ProductEntity, QualityDegradationRuleEntity> entry : productMap.entrySet()) {
            ProductEntity product = entry.getKey();
            QualityDegradationRuleEntity rule = entry.getValue();

            // Calculate how many times the degradation should be applied
            if (daysPassed >= rule.getDegradationAfterDays()) {
                int numDegradations = daysPassed / rule.getDegradationAfterDays();
                // Apply degradation multiple times
                for (int i = 0; i < numDegradations; i++) {
                    int newQuality = product.getQualityInPercent() - rule.getDegradationInPercentPoints();
                    product.setQualityInPercent(Math.max(0, newQuality));
                    if (product.getQualityInPercent() <= product.getMinimumQualityInPercent()) {
                        product.setMarkedForRemoval(true);
                    }
                    product = applySpecialRules(product);
                }
            }
            modifiedProducts.add(product); // Add updated product to new list
        }

        return modifiedProducts;
    }

    public QualityDegradationRuleEntity getByProductEntityName(String name) {
        return qualityDegradationRepository.findQualityDegradationRuleEntityByName(name);
    }

    private ProductEntity applySpecialRules(ProductEntity product) {
        switch (product.getProductTypeEntity().getName()) {
            //Wine
            case "Wine" -> {
                if (product.getQualityInPercent() > 50) {
                    product.setQualityInPercent(50);
                }
            }
            default -> { //Not rounding with BigDecimal, because I don't like BigDecimal
                product.setBasePrice(Math.round((product.getBasePrice() + (0.10 * product.getQualityInPercent() / 100)) * 100.0) / 100.0);
            }
        }
        return product;
    }
}