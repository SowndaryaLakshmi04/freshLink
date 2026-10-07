package com.freshlink.controller;

import com.freshlink.dto.QualityCheckRequest;
import com.freshlink.model.QualityReport;
import com.freshlink.repository.QualityReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/quality-check")
@CrossOrigin(origins = "*")
public class AiQualityCheckController {

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    private QualityReportRepository qualityReportRepository;
    
    @PostMapping
    public ResponseEntity<Map> analyzeCrop(@RequestBody QualityCheckRequest request) {
        String pythonMicroserviceUrl = "http://localhost:8000/predict";

        Map<String, String> requestBody = Map.of(
            "cropType", request.cropType() != null ? request.cropType() : "Unknown",
            "imageBase64", request.imageBase64() != null ? request.imageBase64() : "",
            "role", request.role() != null ? request.role() : "farmer"
        );

        try {
            // 1. Send to Python and receive response as a Map
            ResponseEntity<Map> response = restTemplate.postForEntity(pythonMicroserviceUrl, requestBody, Map.class);
            Map<String, Object> root = response.getBody();
            
            // 2. Save directly to PostgreSQL Database
            if (root != null) {
                QualityReport report = new QualityReport();
                report.setCropDetected((String) root.get("cropDetected"));
                report.setOverallScore((Integer) root.get("overallScore"));
                report.setGrade((String) root.get("grade"));
                report.setFreshness((Integer) root.get("freshness"));
                report.setColor((Integer) root.get("color"));
                report.setSize((Integer) root.get("size"));
                report.setDefects((Integer) root.get("defects"));
                report.setSuggestedPrice((Integer) root.get("suggestedPrice"));
                report.setPriceMin((Integer) root.get("priceMin"));
                report.setPriceMax((Integer) root.get("priceMax"));
                report.setGradeReason((String) root.get("gradeReason"));
                report.setBuyerVerdict((String) root.get("buyerVerdict"));
                report.setBuyerAdvice((String) root.get("buyerAdvice"));
                
                qualityReportRepository.save(report);
                System.out.println("✅ Saved new Quality Report to PostgreSQL Database!");
            }
            
            // 3. Return Python's answer to your Frontend
            return ResponseEntity.ok(root);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to reach Python Microservice"));
        }
    }
}