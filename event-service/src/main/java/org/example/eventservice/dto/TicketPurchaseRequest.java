package org.example.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketPurchaseRequest {
    private Long eventId;
    private String buyerEmail;
    private Double price;
    private Integer quantity;
}