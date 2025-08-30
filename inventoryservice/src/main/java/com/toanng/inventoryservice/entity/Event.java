package com.toanng.inventoryservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor

@NoArgsConstructor
@Table(name = "event")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "total_capacity")
    Long totalCapacity;

    @Column(name = "left_capacity")
    Long leftCapacity;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    Venue venue;

    @Column(name = "ticket_price")
    BigDecimal ticketPrice;

}
