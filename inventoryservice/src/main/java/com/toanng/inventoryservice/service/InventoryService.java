package com.toanng.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.toanng.inventoryservice.entity.Event;
import com.toanng.inventoryservice.entity.Venue;
import com.toanng.inventoryservice.repository.EventRepository;
import com.toanng.inventoryservice.repository.VenueRepository;
import com.toanng.inventoryservice.response.EventInventoryResponse;
import com.toanng.inventoryservice.response.VenueInventoryResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryService {

    EventRepository eventRepository;
    VenueRepository venueRepository;

    public List<EventInventoryResponse> getAllEvents() {

        final List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue()).build()).collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueInformation(final Long venueId) {
        final Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("venue not exists"));
        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();

    }

    public EventInventoryResponse getEventInventory(final Long eventId) {

        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return EventInventoryResponse.builder()
                .venue(event.getVenue())
                .eventId(event.getId())
                .capacity(event.getLeftCapacity())
                .ticketPrice(event.getTicketPrice())
                .event(event.getName())
                .build();

    }

}
