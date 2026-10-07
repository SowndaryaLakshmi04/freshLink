package com.freshlink.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long listingId;
    private String cropName;
    private String farmerName;
    private String buyerName;
    
    private double totalAmount;
    private double transportFee;
    private double platformFee;
    private double farmerReceives;
    
    private String escrowStatus; // "IN_ESCROW" or "PAID"
    private String transactionRef;
    private String date;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getListingId() { return listingId; }
    public void setListingId(Long listingId) { this.listingId = listingId; }

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }

    public String getFarmerName() { return farmerName; }
    public void setFarmerName(String farmerName) { this.farmerName = farmerName; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getTransportFee() { return transportFee; }
    public void setTransportFee(double transportFee) { this.transportFee = transportFee; }

    public double getPlatformFee() { return platformFee; }
    public void setPlatformFee(double platformFee) { this.platformFee = platformFee; }

    public double getFarmerReceives() { return farmerReceives; }
    public void setFarmerReceives(double farmerReceives) { this.farmerReceives = farmerReceives; }

    public String getEscrowStatus() { return escrowStatus; }
    public void setEscrowStatus(String escrowStatus) { this.escrowStatus = escrowStatus; }

    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}