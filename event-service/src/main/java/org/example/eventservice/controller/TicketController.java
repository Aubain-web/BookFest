package org.example.eventservice.controller;

import org.example.eventservice.dto.TicketDTO;
import org.example.eventservice.dto.TicketPurchaseRequest;
import org.example.eventservice.entity.TicketEntity;
import org.example.eventservice.service.TicketService;
import org.example.eventservice.service.TicketPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketPublisher ticketPublisher;
    private final RestTemplate restTemplate;

    @Autowired
    public TicketController(TicketService ticketService, TicketPublisher ticketPublisher, RestTemplate restTemplate) {
        this.ticketService = ticketService;
        this.ticketPublisher = ticketPublisher;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/tickets/{eventId}")
    public ResponseEntity<List<TicketEntity>> getTicketsByEventId(@PathVariable Long eventId) {
        List<TicketEntity> tickets = ticketService.getTicketsByEventId(eventId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/purchase-ticket")
    public ResponseEntity<String> purchaseTicket(@RequestBody TicketPurchaseRequest request) {
        TicketDTO ticketDTO = TicketDTO.builder()
                .eventId(request.getEventId())
                .buyerEmail(request.getBuyerEmail())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .status("Pending")
                .build();

        //ticketPublisher.sendTicketMessage(ticketDTO);

        return ResponseEntity.ok("Ticket purchase initiated. You will receive a confirmation shortly.");
    }

    package org.example.eventservice.controller;

import org.example.eventservice.dto.TicketDTO;
import org.example.eventservice.dto.TicketPurchaseRequest;
import org.example.eventservice.entity.TicketEntity;
import org.example.eventservice.service.TicketService;
import org.example.eventservice.service.TicketPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

    @RestController
    @RequestMapping("api/tickets")
    public class TicketController {

        private final TicketService ticketService;
        private final TicketPublisher ticketPublisher;
        private final RestTemplate restTemplate;

        @Autowired
        public TicketController(TicketService ticketService, TicketPublisher ticketPublisher, RestTemplate restTemplate) {
            this.ticketService = ticketService;
            this.ticketPublisher = ticketPublisher;
            this.restTemplate = restTemplate;
        }

        @GetMapping("/api/tickets/{eventId}")
        public ResponseEntity<List<TicketEntity>> getTicketsByEventId(@PathVariable Long eventId) {
            List<TicketEntity> tickets = ticketService.getTicketsByEventId(eventId);
            return ResponseEntity.ok(tickets);
        }

        @PostMapping("/purchase-ticket")
        public ResponseEntity<String> purchaseTicket(@RequestBody TicketPurchaseRequest request) {
            TicketDTO ticketDTO = TicketDTO.builder()
                    .eventId(request.getEventId())
                    .buyerEmail(request.getBuyerEmail())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .status("Pending")
                    .build();

            //ticketPublisher.sendTicketMessage(ticketDTO);

            return ResponseEntity.ok("Ticket purchase initiated. You will receive a confirmation shortly.");
        }

        @GetMapping("/my-tickets")
        public ResponseEntity<List<TicketDTO>> getMyTickets(Authentication authentication) {
            String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

            ResponseEntity<List<TicketDTO>> response = restTemplate.exchange(
                    "http://event-service/api/tickets/buyer/" + userEmail,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TicketDTO>>() {}
            );

            return response;
        }

        @GetMapping("/buyer/{email}")
        public ResponseEntity<List<TicketEntity>> getTicketsByBuyerEmail(@PathVariable String email) {
            List<TicketEntity> tickets = ticketService.getTicketsByBuyerEmail(email);
            return ResponseEntity.ok(tickets);
        }

        @DeleteMapping("/cancel/{id}")
        public ResponseEntity<Void> cancelTicket(@PathVariable Long id) {
            ticketService.cancelTicket(id);
            return ResponseEntity.noContent().build();
        }
    }
}