package com.bramacher.brockhausApplicationProject.repository.h2;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {

    ProductTypeEntity findProductTypeEntityByName(String name);
}
