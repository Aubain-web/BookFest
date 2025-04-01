package org.example.eventservice.controller;

import jakarta.validation.Valid;
import org.example.eventservice.entity.EventEntity;
import org.example.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventEntity> getEventById(@Valid @PathVariable Long id) {
        Optional<EventEntity> event = eventService.findById(id);
        return event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EventEntity> getEventByName(@PathVariable String name) {
        Optional<EventEntity> event = eventService.findByName(name);
        return event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/allevents")
    public ResponseEntity<List<EventEntity>> getAllEvents() {
        List<EventEntity> events = eventService.findAll();
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<EventEntity> createEvent(@Valid  @RequestBody EventEntity eventEntity) {
        EventEntity savedEvent = eventService.save(eventEntity);
        return ResponseEntity.ok(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventEntity> updateEvent(@PathVariable Long id, @Valid @RequestBody EventEntity updatedEvent) {
        try {
            EventEntity event = eventService.updateEvent(id, updatedEvent);
            return ResponseEntity.ok(event);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@Valid @PathVariable Long id) {
        if (!eventService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
