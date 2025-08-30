package com.toanng.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toanng.inventoryservice.entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {

}
