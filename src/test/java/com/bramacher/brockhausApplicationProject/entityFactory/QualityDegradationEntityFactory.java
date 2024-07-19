package com.bramacher.brockhausApplicationProject.entityFactory;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductTypeEntity;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.QualityDegradationRuleEntity;

import java.util.List;
import java.util.ArrayList;

public class QualityDegradationEntityFactory {

    public static List<QualityDegradationRuleEntity> getQualityDegradationRuleEntityListOne() {
        List<ProductTypeEntity> productTypeEntityList = ProductEntityTypeFactory.getProductTypeEntityListOne();

        List<QualityDegradationRuleEntity> qualityDegradationRules = new ArrayList<>();

        // Bread
        QualityDegradationRuleEntity breadRule = new QualityDegradationRuleEntity();
        breadRule.setId(1L);
        breadRule.setName("Bread Rule");
        breadRule.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Bread")).findFirst().get());
        breadRule.setDegradationInPercentPoints(5);
        breadRule.setDegradationAfterDays(3);
        qualityDegradationRules.add(breadRule);

        // Vegetable
        QualityDegradationRuleEntity vegetableRule = new QualityDegradationRuleEntity();
        vegetableRule.setId(2L);
        vegetableRule.setName("Vegetable Rule");
        vegetableRule.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Vegetable")).findFirst().get());
        vegetableRule.setDegradationInPercentPoints(7);
        vegetableRule.setDegradationAfterDays(2);
        qualityDegradationRules.add(vegetableRule);

        // Coffee
        QualityDegradationRuleEntity coffeeRule = new QualityDegradationRuleEntity();
        coffeeRule.setId(3L);
        coffeeRule.setName("Coffee Rule");
        coffeeRule.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Coffee")).findFirst().get());
        coffeeRule.setDegradationInPercentPoints(3);
        coffeeRule.setDegradationAfterDays(5);
        qualityDegradationRules.add(coffeeRule);

        // Candy
        QualityDegradationRuleEntity candyRule = new QualityDegradationRuleEntity();
        candyRule.setId(4L);
        candyRule.setName("Candy Rule");
        candyRule.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Candy")).findFirst().get());
        candyRule.setDegradationInPercentPoints(10);
        candyRule.setDegradationAfterDays(4);
        qualityDegradationRules.add(candyRule);

        // Frozen
        QualityDegradationRuleEntity frozenRule = new QualityDegradationRuleEntity();
        frozenRule.setId(5L);
        frozenRule.setName("Frozen Rule");
        frozenRule.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Frozen")).findFirst().get());
        frozenRule.setDegradationInPercentPoints(2);
        frozenRule.setDegradationAfterDays(6);
        qualityDegradationRules.add(frozenRule);

        return qualityDegradationRules;
    }
}
