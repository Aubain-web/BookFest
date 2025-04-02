package org.example.eventservice.repository;

import org.example.eventservice.entity.TicketEntity;
import org.example.eventservice.entity.TicketSatus.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findByEventId(Long eventId);
    List<TicketEntity> findByBuyerEmail(String buyerEmail);
    List<TicketEntity> findByStatus(TicketStatus status);
}
