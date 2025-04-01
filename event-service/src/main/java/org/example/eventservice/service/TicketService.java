package org.example.eventservice.service;

import org.example.eventservice.entity.TicketEntity;
import org.example.eventservice.entity.TicketSatus.TicketStatus;
import org.example.eventservice.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public TicketEntity createTicket(TicketEntity ticket) {
        ticket.setStatus(TicketStatus.Paid);
        return ticketRepository.save(ticket);
    }

    public TicketEntity getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<TicketEntity> getTicketsByEventId(Long eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    public List<TicketEntity> getTicketsByBuyerEmail(String email) {
        return ticketRepository.findByBuyerEmail(email);
    }

    public List<TicketEntity> getTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status);
    }

    public void cancelTicket(Long id) {
        TicketEntity ticket = getTicketById(id);
        if (ticket != null) {
            ticket.setStatus(TicketStatus.Canceled);
            ticketRepository.save(ticket);
        }
    }

    public void updateTicket(TicketEntity ticket) {
        TicketEntity existingTicket = getTicketById(ticket.getId());
        if (existingTicket != null) {
            existingTicket.setStatus(ticket.getStatus());
            ticketRepository.save(existingTicket);
        }
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
