package org.example.eventservice.controller;

import org.example.eventservice.dto.TicketDTO;
import org.example.eventservice.entity.TicketEntity;
import org.example.eventservice.service.JwtService;
import org.example.eventservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final RestTemplate restTemplate;
    private final JwtService jwtService;

    @Autowired
    public TicketController(TicketService ticketService, RestTemplate restTemplate, JwtService jwtService) {
        this.ticketService = ticketService;
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{eventId}")
    public ResponseEntity<List<TicketEntity>> getTicketsByEventId(@PathVariable Long eventId) {
        List<TicketEntity> tickets = ticketService.getTicketsByEventId(eventId);
        return ResponseEntity.ok(tickets);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/buy-ticket")
    public ResponseEntity<TicketDTO> buyTicket(
            @RequestBody TicketDTO request,
            @RequestHeader("Authorization") String token // 🔥 Vérifier le JWT
    ) {
        if (!jwtService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        TicketDTO purchasedTicket = ticketService.purchaseTicket(
                request.getId(), request.getEventId(), request.getTicketType()
        );

        return ResponseEntity.ok(purchasedTicket);
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

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicket(id);
        return ResponseEntity.noContent().build();
    }
}