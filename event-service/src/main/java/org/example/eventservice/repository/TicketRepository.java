package org.example.eventservice.repository;

import org.example.eventservice.entity.TicketEntity;
import org.example.eventservice.entity.TicketSatus.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findByEventId(Long eventId);
    List<TicketEntity> findByBuyerEmail(String buyerEmail);
    List<TicketEntity> findByStatus(TicketStatus status);
}
