package com.freshlink.controller;

import com.freshlink.model.Listing;
import com.freshlink.model.ListingStatus;
import com.freshlink.model.Payment;
import com.freshlink.repository.ListingRepository;
import com.freshlink.repository.PaymentRepository; // 1. Import your PaymentRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "*")
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private PaymentRepository paymentRepository; // 2. Inject it right here!

    // ===================== YOUR ENDPOINTS =====================
    
    @GetMapping
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @PostMapping
    public Listing createListing(@RequestBody Listing listing) {
        return listingRepository.save(listing);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<Listing> acceptListing(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Listing> listingOpt = listingRepository.findById(id);
        if (listingOpt.isPresent()) {
            Listing listing = listingOpt.get();
            
            // Fix 1: Use ListingStatus enum instead of raw string
            listing.setStatus(ListingStatus.valueOf("TRANSIT"));
            
            listing.setBuyer(body.get("buyer"));
            
            // Fix 2: Pass an Integer (10) instead of double (10.0)
            listing.setTruckPos(10);
            
            Listing updated = listingRepository.save(listing);

            // Automatically create an Escrow Payment record in PostgreSQL
            double total = listing.getQty() * listing.getPrice();
            double transport = Math.round(total * 0.08);
            double platform = Math.round(total * 0.02);
            double farmerGets = total - transport - platform;

            Payment payment = new Payment();
            payment.setListingId(listing.getId());
            payment.setCropName(listing.getCrop());
            payment.setFarmerName(listing.getFarmer());
            payment.setBuyerName(listing.getBuyer());
            payment.setTotalAmount(total);
            payment.setTransportFee(transport);
            payment.setPlatformFee(platform);
            payment.setFarmerReceives(farmerGets);
            payment.setEscrowStatus("IN_ESCROW");
            
            // Fix 3: Convert LocalDate to String using .toString()
            payment.setDate(listing.getHarvestDate() != null ? listing.getHarvestDate().toString() : "");
            
            paymentRepository.save(payment);

            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/confirm-delivery")
    public ResponseEntity<Listing> confirmDelivery(@PathVariable Long id) {
        Optional<Listing> listingOpt = listingRepository.findById(id);
        if (listingOpt.isPresent()) {
            Listing listing = listingOpt.get();
            
            // Fix 1: Use ListingStatus enum here too
            listing.setStatus(ListingStatus.valueOf("DELIVERED"));
            
            Listing updated = listingRepository.save(listing);

            // Release Escrow Funds in PostgreSQL
            Optional<Payment> paymentOpt = paymentRepository.findByListingId(id);
            if (paymentOpt.isPresent()) {
                Payment payment = paymentOpt.get();
                payment.setEscrowStatus("PAID");
                payment.setTransactionRef("TXN" + id + "2026");
                paymentRepository.save(payment);
            }

            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        if (listingRepository.existsById(id)) {
            listingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}