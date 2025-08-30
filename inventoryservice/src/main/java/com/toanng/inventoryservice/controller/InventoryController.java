package com.toanng.inventoryservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toanng.inventoryservice.response.EventInventoryResponse;
import com.toanng.inventoryservice.response.VenueInventoryResponse;
import com.toanng.inventoryservice.service.InventoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1")
public class InventoryController {

    InventoryService inventoryService;

    @GetMapping("/inventory/events")
    public List<EventInventoryResponse> inventoryGetAllEvents() {
        return inventoryService.getAllEvents();
    }

    @GetMapping("/inventory/venue/{venueId}")
    public VenueInventoryResponse inventoryByVenueId(@PathVariable Long venueId) {
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("/inventory/event/{eventId}")
    public EventInventoryResponse inventoryForEvent(@PathVariable Long eventId) {
        return inventoryService.getEventInventory(eventId);
    }
}
