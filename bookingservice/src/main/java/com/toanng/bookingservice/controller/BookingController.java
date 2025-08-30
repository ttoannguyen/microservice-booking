package com.toanng.bookingservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toanng.bookingservice.request.BookingRequest;
import com.toanng.bookingservice.response.BookingResponse;
import com.toanng.bookingservice.service.BookingService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

    BookingService bookingService;

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/booking")
    public BookingResponse createBooking(@RequestBody BookingRequest entity) {
        return bookingService.createBooking(entity);
    }

}
