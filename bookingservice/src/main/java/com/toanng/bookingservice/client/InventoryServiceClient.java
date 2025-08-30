package com.toanng.bookingservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.toanng.bookingservice.response.InventoryResponse;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    String inventoryServiceUrl;

    public InventoryResponse getInventory(final Long eventId) {
        final RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + eventId, InventoryResponse.class);
    }

}
