package com.event.scheduler;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findByLocationContainingIgnoreCase(String location);
    List<Event> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}