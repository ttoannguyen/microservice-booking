package com.toanng.inventoryservice.response;

import java.math.BigDecimal;

import com.toanng.inventoryservice.entity.Venue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventInventoryResponse {
    Long eventId;
    String event;
    Long capacity;
    Venue venue;
    BigDecimal ticketPrice;

}
