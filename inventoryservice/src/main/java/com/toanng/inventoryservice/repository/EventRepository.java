package com.toanng.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toanng.inventoryservice.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
