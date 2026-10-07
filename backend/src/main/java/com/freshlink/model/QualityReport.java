package com.freshlink.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "quality_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropDetected;
    private Integer overallScore;
    private String grade;
    private Integer freshness;
    private Integer color;
    private Integer size;
    private Integer defects;
    
    private Integer suggestedPrice;
    private Integer priceMin;
    private Integer priceMax;

    @Column(columnDefinition = "TEXT")
    private String gradeReason;
    
    private String buyerVerdict;
    
    @Column(columnDefinition = "TEXT")
    private String buyerAdvice;

    private LocalDateTime analyzedAt = LocalDateTime.now();
}