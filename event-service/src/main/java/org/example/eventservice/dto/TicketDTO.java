package org.example.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
    private Long id;
    private Long eventId;
    private String buyerEmail;
    private Double price;
    private Integer quantity;
    private LocalDateTime purchaseDate;
    private String status;
}