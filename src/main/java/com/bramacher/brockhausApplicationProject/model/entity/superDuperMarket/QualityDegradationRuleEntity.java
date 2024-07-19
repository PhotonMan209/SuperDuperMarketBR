package com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket;

import jakarta.persistence.*;

@Entity
public class QualityDegradationRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    ProductTypeEntity productTypeEntity;
    private int degradationInPercentPoints;
    private int degradationAfterDays;

    public QualityDegradationRuleEntity() {
    }

    public QualityDegradationRuleEntity(long id, String name, ProductTypeEntity productTypeEntity, int degradationInPercentPoints, int degradationAfterDays) {
        this.id = id;
        this.name = name;
        this.productTypeEntity = productTypeEntity;
        this.degradationInPercentPoints = degradationInPercentPoints;
        this.degradationAfterDays = degradationAfterDays;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductTypeEntity getProductTypeEntity() {
        return productTypeEntity;
    }

    public void setProductTypeEntity(ProductTypeEntity productTypeEntity) {
        this.productTypeEntity = productTypeEntity;
    }

    public int getDegradationInPercentPoints() {
        return degradationInPercentPoints;
    }

    public void setDegradationInPercentPoints(int degradationInPercentPoints) {
        this.degradationInPercentPoints = degradationInPercentPoints;
    }

    public int getDegradationAfterDays() {
        return degradationAfterDays;
    }

    public void setDegradationAfterDays(int degradationAfterDays) {
        this.degradationAfterDays = degradationAfterDays;
    }
}
