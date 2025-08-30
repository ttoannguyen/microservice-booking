package com.toanng.bookingservice.service;

import org.springframework.stereotype.Service;

import com.toanng.bookingservice.client.InventoryServiceClient;
import com.toanng.bookingservice.entity.Customer;
import com.toanng.bookingservice.repository.CustomerRepository;
import com.toanng.bookingservice.request.BookingRequest;
import com.toanng.bookingservice.response.BookingResponse;
import com.toanng.bookingservice.response.InventoryResponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    CustomerRepository customerRepository;
    InventoryServiceClient inventoryServiceClient;

    public BookingResponse createBooking(final BookingRequest request) {

        final Customer customer = customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        System.out.println("Inventory service response" + inventoryResponse);

        if (inventoryResponse.getCapacity() < request.getTicketCount())
            throw new RuntimeException("Not enough inventory");

        return BookingResponse.builder().build();
    }

}
