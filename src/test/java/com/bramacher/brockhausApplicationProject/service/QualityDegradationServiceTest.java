package com.bramacher.brockhausApplicationProject.service;

import com.bramacher.brockhausApplicationProject.entityFactory.ProductEntityFactory;
import com.bramacher.brockhausApplicationProject.entityFactory.QualityDegradationEntityFactory;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductEntity;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.QualityDegradationRuleEntity;
import com.bramacher.brockhausApplicationProject.service.database.QualityDegradationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QualityDegradationServiceTest {

    @InjectMocks
    private QualityDegradationService qualityDegradationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testQualityDegradationBehaviorPrice() {
        // Arrange
        List<ProductEntity> productList = ProductEntityFactory.getProductEntityListOne();
        List<QualityDegradationRuleEntity> ruleList = QualityDegradationEntityFactory.getQualityDegradationRuleEntityListOne();
        Map<ProductEntity, QualityDegradationRuleEntity> map = mappingOfProductAndRule(productList, ruleList);
        int daysOfDegradation = 3;

        // Act
        List<ProductEntity> updatedList = qualityDegradationService.degradeProductByCategory(map, daysOfDegradation);

        // Assert
        ProductEntity baguette = getByName(updatedList, "Bread");
        assertEquals(2.10, baguette.getBasePrice(), "Product One is correct");
        ProductEntity vegetable = getByName(updatedList, "Vegetable");
        assertEquals(1.57, vegetable.getBasePrice(), "Product Two is correct");
        ProductEntity coffee = getByName(updatedList, "Coffee");
        assertEquals(5.00, coffee.getBasePrice(), "Product Three is correct");

    }

    @Test
    public void testQualityDegradationBehaviorQuality() {
        // Arrange
        List<ProductEntity> productList = ProductEntityFactory.getProductEntityListOne();
        List<QualityDegradationRuleEntity> ruleList = QualityDegradationEntityFactory.getQualityDegradationRuleEntityListOne();
        Map<ProductEntity, QualityDegradationRuleEntity> map = mappingOfProductAndRule(productList, ruleList);
        int daysOfDegradation = 15;

        // Act
        List<ProductEntity> updatedList = qualityDegradationService.degradeProductByCategory(map, daysOfDegradation);

        // Assert
        ProductEntity baguette = getByName(updatedList, "Bread");
        assertEquals(75, baguette.getQualityInPercent(), "Product One is correct");
        ProductEntity vegetable = getByName(updatedList, "Vegetable");
        assertEquals(31, vegetable.getQualityInPercent(), "Product Two is correct");
        ProductEntity coffee = getByName(updatedList, "Coffee");
        assertEquals(81, coffee.getQualityInPercent(), "Product Three is correct");
    }

    @Test
    public void testQualityDegradationBehaviorOffSupermarketShelf() {
        // Arrange
        List<ProductEntity> productList = ProductEntityFactory.getProductEntityListOne();
        List<QualityDegradationRuleEntity> ruleList = QualityDegradationEntityFactory.getQualityDegradationRuleEntityListOne();
        Map<ProductEntity, QualityDegradationRuleEntity> map = mappingOfProductAndRule(productList, ruleList);
        int daysOfDegradation = 100;

        // Act
        List<ProductEntity> updatedList = qualityDegradationService.degradeProductByCategory(map, daysOfDegradation);

        // Assert
        ProductEntity baguette = getByName(updatedList, "Bread");
        assertEquals(true, baguette.isMarkedForRemoval(), "Product One is correct");
        ProductEntity vegetable = getByName(updatedList, "Vegetable");
        assertEquals(true, vegetable.isMarkedForRemoval(), "Product Two is correct");
        ProductEntity coffee = getByName(updatedList, "Coffee");
        assertEquals(true, coffee.isMarkedForRemoval(), "Product Three is correct");
    }

    private Map<ProductEntity, QualityDegradationRuleEntity> mappingOfProductAndRule(List<ProductEntity> productEntityList, List<QualityDegradationRuleEntity> qualityDegradationRuleEntityList) {
        return productEntityList.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        p -> qualityDegradationRuleEntityList.stream()
                                .filter(rule -> rule.getProductTypeEntity().getName().equals(p.getProductTypeEntity().getName()))
                                .findFirst()
                                .orElse(null)
                ));
    }

    private ProductEntity getByName(List<ProductEntity> productEntityList, String name) {
        return productEntityList.stream().filter(p -> p.getProductTypeEntity().getName().equals(name)).findFirst().orElse(null);
    }
}
