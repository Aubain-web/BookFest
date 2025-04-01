package org.example.eventservice.controller;

import org.example.eventservice.entity.TicketEntity;
import org.example.eventservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/api/tickets/{eventId}")
    public ResponseEntity<List<TicketEntity>> getTicketsByEventId(@PathVariable Long eventId) {
        List<TicketEntity> tickets = ticketService.getTicketsByEventId(eventId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/buy")
    public ResponseEntity<TicketEntity> createTicket(@RequestBody TicketEntity ticket) {
        TicketEntity createdTicket = ticketService.createTicket(ticket);
        return ResponseEntity.ok(createdTicket);
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
