package com.freshlink.repository;

import com.freshlink.model.Listing;
import com.freshlink.model.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    // This custom method automatically generates SQL to find listings by status (e.g., all "AVAILABLE" crops)
    List<Listing> findByStatus(ListingStatus status);
}