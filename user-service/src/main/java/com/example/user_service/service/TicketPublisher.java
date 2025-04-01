package com.example.user_service.service;

import com.example.user_service.Config.RabbitmqConfig;
import com.example.user_service.dto.TicketDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, RabbitmqConfig.QUEUE_NAME, ticketDTO);
        System.out.println(" Ticket message sent to RabbitMQ: " + ticketDTO);
    }

    @RabbitListener(queues = "ticketQueue")
    public void receiveTicketMessage(TicketDTO ticketDTO) {

        System.out.println(" Ticket message received: " + ticketDTO);
    }
}