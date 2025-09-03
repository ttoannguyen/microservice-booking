package com.toanng.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    String inventoryServiceUrl;

    public ResponseEntity<Void> updateInventory(final Long eventId, final Long ticketCount) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(inventoryServiceUrl + "/event/" + eventId + "/capacity/" + ticketCount, null);

        return ResponseEntity.ok().build();
    }

}
