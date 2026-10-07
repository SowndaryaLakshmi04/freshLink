package com.freshlink.repository;

import com.freshlink.model.QualityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityReportRepository extends JpaRepository<QualityReport, Long> {
    // This custom method will let us fetch the most recent analyses first
    List<QualityReport> findAllByOrderByAnalyzedAtDesc();
}