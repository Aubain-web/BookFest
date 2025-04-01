package org.example.eventservice.service;

import org.example.eventservice.config.RabbitmqConfig;
import org.example.eventservice.dto.TicketDTO;
import org.example.eventservice.entity.EventEntity;
import org.example.eventservice.repository.EventRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TicketConsumer {
    private final EventRepository eventRepository;

    public TicketConsumer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RabbitListener(queues = RabbitmqConfig.QUEUE_NAME)
    public void receiveTicketMessage(TicketDTO ticketDTO) {
        System.out.println(" Ticket message received: " + ticketDTO);

        EventEntity event = eventRepository.findById(ticketDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        System.out.println(" Event found: " + event.getName());
    }
}