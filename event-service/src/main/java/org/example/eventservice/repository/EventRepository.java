package org.example.eventservice.repository;

import org.example.eventservice.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    //Optional<EventEntity> findById(Long id);
    Optional<EventEntity> findByName(String name);
    List<EventEntity> findByNameContainingIgnoreCase(String name);

    List<EventEntity> findByCategory(String category);
    List<EventEntity> findByLocation(String location);
    List<EventEntity> findByDate(String date);

    List<EventEntity> findByCategoryAndLocation(String category, String location);
    List<EventEntity> findByCategoryAndDate(String category, String date);
    List<EventEntity> findByLocationAndDate(String location, String date);

    List<EventEntity> findByPriceBetween(Double minPrice, Double maxPrice);
    List<EventEntity> findByCapacityBetween(Integer minCapacity, Integer maxCapacity);

}
