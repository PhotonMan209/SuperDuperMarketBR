package com.bramacher.brockhausApplicationProject.repository.h2;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
