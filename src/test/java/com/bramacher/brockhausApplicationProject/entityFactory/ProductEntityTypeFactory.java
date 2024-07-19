package com.bramacher.brockhausApplicationProject.entityFactory;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductTypeEntity;

import java.util.List;

public class ProductEntityTypeFactory {

    public static List<ProductTypeEntity> getProductTypeEntityListOne() {
        ProductTypeEntity productTypeEntity1 = new ProductTypeEntity();
        productTypeEntity1.setId(1L);
        productTypeEntity1.setName("Bread");

        ProductTypeEntity productTypeEntity2 = new ProductTypeEntity();
        productTypeEntity2.setId(2L);
        productTypeEntity2.setName("Vegetable");

        ProductTypeEntity productTypeEntity3 = new ProductTypeEntity();
        productTypeEntity3.setId(3L);
        productTypeEntity3.setName("Coffee");

        ProductTypeEntity productTypeEntity4 = new ProductTypeEntity();
        productTypeEntity4.setId(4L);
        productTypeEntity4.setName("Candy");

        ProductTypeEntity productTypeEntity5 = new ProductTypeEntity();
        productTypeEntity5.setId(5L);
        productTypeEntity5.setName("Frozen");

        return List.of(productTypeEntity1, productTypeEntity2, productTypeEntity3, productTypeEntity4, productTypeEntity5);
    }
}
