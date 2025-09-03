package com.toanng.bookingservice.service;

import java.math.BigDecimal;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.toanng.bookingservice.client.InventoryServiceClient;
import com.toanng.bookingservice.entity.Customer;
import com.toanng.bookingservice.event.BookingEvent;
import com.toanng.bookingservice.repository.CustomerRepository;
import com.toanng.bookingservice.request.BookingRequest;
import com.toanng.bookingservice.response.BookingResponse;
import com.toanng.bookingservice.response.InventoryResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    CustomerRepository customerRepository;
    InventoryServiceClient inventoryServiceClient;

    KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public BookingResponse createBooking(final BookingRequest request) {

        final Customer customer = customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        log.info("Inventory service response {}", inventoryResponse);

        if (inventoryResponse.getCapacity() < request.getTicketCount())
            throw new RuntimeException("Not enough inventory");

        BookingEvent bookingEvent = createBookingEvent(request, customer, inventoryResponse);
        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking send to kafka {}", bookingEvent);

        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice()).build();
    }

    private BookingEvent createBookingEvent(final BookingRequest request, final Customer customer,
            final InventoryResponse inventoryResponse) {

        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(request.getEventId())
                .ticketCount(request.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();

    }

}
