package com.freshlink.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "listings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crop;
    private Integer qty;
    private Double price;
    private LocalDate harvestDate;
    private String location;
    private String quality;
    
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    private ListingStatus status = ListingStatus.AVAILABLE;

    private String farmer;
    private String buyer;
    private Integer truckPos = 0;

    @Column(columnDefinition = "TEXT")
    private String customImg; 
}