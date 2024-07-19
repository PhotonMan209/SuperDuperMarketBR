package com.bramacher.brockhausApplicationProject.repository.h2;

import com.bramacher.brockhausApplicationProject.model.entity.superDuperMarket.QualityDegradationRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityDegradationRepository extends JpaRepository<QualityDegradationRuleEntity, Long> {
    //easy Hibernate query. More complex Queries require @Query etc.
    QualityDegradationRuleEntity findQualityDegradationRuleEntityByName (String name);
}
