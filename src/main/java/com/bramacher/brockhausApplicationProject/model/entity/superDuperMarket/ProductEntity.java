package com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket;


import jakarta.persistence.*;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productTypeEntity;
    private int qualityInPercent;
    private int minimumQualityInPercent;
    private boolean markedForRemoval;
    private LocalDate expireDate;
    private double basePrice;
    private LocalDate lastModifiedManual;
    private LocalDate lastModifiedAutomatic;

    public ProductEntity() {
    }

    public ProductEntity(long id, String name, ProductTypeEntity productTypeEntity, int qualityInPercent, int minimumQualityInPercent, boolean markedForRemoval, LocalDate expireDate, double basePrice, LocalDate lastModifiedManual, LocalDate lastModifiedAutomatic) {
        this.id = id;
        this.name = name;
        this.productTypeEntity = productTypeEntity;
        this.qualityInPercent = qualityInPercent;
        this.minimumQualityInPercent = minimumQualityInPercent;
        this.markedForRemoval = markedForRemoval;
        this.expireDate = expireDate;
        this.basePrice = basePrice;
        this.lastModifiedManual = lastModifiedManual;
        this.lastModifiedAutomatic = lastModifiedAutomatic;
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

    public int getQualityInPercent() {
        return qualityInPercent;
    }

    public void setQualityInPercent(int qualityInPercent) {
        this.qualityInPercent = qualityInPercent;
    }

    public int getMinimumQualityInPercent() {
        return minimumQualityInPercent;
    }

    public void setMinimumQualityInPercent(int minimumQualityInPercent) {
        this.minimumQualityInPercent = minimumQualityInPercent;
    }

    public boolean isMarkedForRemoval() {
        return markedForRemoval;
    }

    public void setMarkedForRemoval(boolean markedForRemoval) {
        this.markedForRemoval = markedForRemoval;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public LocalDate getLastModifiedManual() {
        return lastModifiedManual;
    }

    public void setLastModifiedManual(LocalDate lastModifiedManual) {
        this.lastModifiedManual = lastModifiedManual;
    }

    public LocalDate getLastModifiedAutomatic() {
        return lastModifiedAutomatic;
    }

    public void setLastModifiedAutomatic(LocalDate lastModifiedAutomatic) {
        this.lastModifiedAutomatic = lastModifiedAutomatic;
    }
}


