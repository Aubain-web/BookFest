package org.example.eventservice.service;


import org.example.eventservice.config.RabbitmqConfig;
import org.example.eventservice.dto.TicketDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TicketPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTicketMessage(TicketDTO ticketDTO) {
        rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_NAME, ticketDTO);
        System.out.println("Ticket message sent to queue: " + ticketDTO);
    }
}
