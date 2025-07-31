package com.event.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private EventRepository repo;

    public List<Event> getAll() {
        return repo.findAll();
    }

    public Event create(Event event) {
        return repo.save(event);
    }

    public Event update(Long id, Event updated) {
        return repo.findById(id).map(event -> {
            event.setTitle(updated.getTitle());
            event.setDescription(updated.getDescription());
            event.setLocation(updated.getLocation());
            event.setDateTime(updated.getDateTime());
            event.setStatus(updated.getStatus());
            return repo.save(event);
        }).orElseThrow();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Event> search(String title, String location, LocalDateTime start, LocalDateTime end) {
        if (title != null) return repo.findByTitleContainingIgnoreCase(title);
        if (location != null) return repo.findByLocationContainingIgnoreCase(location);
        if (start != null && end != null) return repo.findByDateTimeBetween(start, end);
        return repo.findAll();
    }

    public String summarize(String content) {
        return content.split("\\.")[0] + ".";
    }
}