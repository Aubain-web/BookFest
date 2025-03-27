package org.example.eventservice.service;

import org.example.eventservice.entity.EventEntity;
import org.example.eventservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<EventEntity> findByName(String name) {
        return eventRepository.findByName(name);
    }

    public Optional<EventEntity> findById(Long id) {
        return eventRepository.findById(id);
    }

    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    public EventEntity save(EventEntity eventEntity) {
        return eventRepository.save(eventEntity);
    }

    public EventEntity updateEvent(Long id, EventEntity updatedEvent) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setName(updatedEvent.getName());
                    existingEvent.setDescription(updatedEvent.getDescription());
                    existingEvent.setLocation(updatedEvent.getLocation());
                    existingEvent.setDate(updatedEvent.getDate());
                    existingEvent.setTime(updatedEvent.getTime());
                    existingEvent.setDuration(updatedEvent.getDuration());
                    existingEvent.setCategory(updatedEvent.getCategory());
                    existingEvent.setPrice(updatedEvent.getPrice());
                    existingEvent.setCapacity(updatedEvent.getCapacity());
                    existingEvent.setImageUrl(updatedEvent.getImageUrl());
                    return eventRepository.save(existingEvent);
                }).orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return eventRepository.existsById(id);
    }

    public long count() {
        return eventRepository.count();
    }

}
