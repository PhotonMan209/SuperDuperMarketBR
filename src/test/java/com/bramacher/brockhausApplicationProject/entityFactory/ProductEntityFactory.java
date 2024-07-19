package com.bramacher.brockhausApplicationProject.entityFactory;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductEntity;
import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductTypeEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductEntityFactory {

    public static List<ProductEntity> getProductEntityListOne() {
        List<ProductTypeEntity> productTypeEntityList = ProductEntityTypeFactory.getProductTypeEntityListOne();

        ProductEntity product1 = new ProductEntity();
        product1.setId(1L);
        product1.setName("Baguette");
        product1.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Bread")).findFirst().get());
        product1.setQualityInPercent(100);
        product1.setMinimumQualityInPercent(40);
        product1.setMarkedForRemoval(false);
        product1.setExpireDate(LocalDate.of(2024, 10, 1));
        product1.setBasePrice(2.00);
        product1.setLastModifiedAutomatic(LocalDate.now());
        product1.setLastModifiedManual(LocalDate.now());

        ProductEntity product2 = new ProductEntity();
        product2.setId(2L);
        product2.setName("Carrot");
        product2.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Vegetable")).findFirst().get());
        product2.setQualityInPercent(80);
        product2.setMinimumQualityInPercent(30);
        product2.setMarkedForRemoval(false);
        product2.setExpireDate(LocalDate.of(2024, 11, 1));
        product2.setBasePrice(1.50);
        product2.setLastModifiedAutomatic(LocalDate.now());
        product2.setLastModifiedManual(LocalDate.now());

        ProductEntity product3 = new ProductEntity();
        product3.setId(3L);
        product3.setName("Espresso");
        product3.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Coffee")).findFirst().get());
        product3.setQualityInPercent(90);
        product3.setMinimumQualityInPercent(50);
        product3.setMarkedForRemoval(false);
        product3.setExpireDate(LocalDate.of(2024, 12, 1));
        product3.setBasePrice(5.00);
        product3.setLastModifiedAutomatic(LocalDate.now());
        product3.setLastModifiedManual(LocalDate.now());

        ProductEntity product4 = new ProductEntity();
        product4.setId(4L);
        product4.setName("Chocolate Bar");
        product4.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Candy")).findFirst().get());
        product4.setQualityInPercent(70);
        product4.setMinimumQualityInPercent(20);
        product4.setMarkedForRemoval(false);
        product4.setExpireDate(LocalDate.of(2024, 10, 15));
        product4.setBasePrice(3.00);
        product4.setLastModifiedAutomatic(LocalDate.now());
        product4.setLastModifiedManual(LocalDate.now());

        ProductEntity product5 = new ProductEntity();
        product5.setId(5L);
        product5.setName("Frozen Pizza");
        product5.setProductTypeEntity(productTypeEntityList.stream().filter(pt -> pt.getName().equals("Frozen")).findFirst().get());
        product5.setQualityInPercent(85);
        product5.setMinimumQualityInPercent(40);
        product5.setMarkedForRemoval(false);
        product5.setExpireDate(LocalDate.of(2024, 11, 15));
        product5.setBasePrice(6.00);
        product5.setLastModifiedAutomatic(LocalDate.now());
        product5.setLastModifiedManual(LocalDate.now());

        return new ArrayList<>(List.of(product1, product2, product3, product4, product5));
    }

}
