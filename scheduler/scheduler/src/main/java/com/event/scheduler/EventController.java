package com.event.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    @Autowired
    private EventService service;
    @Autowired
    private OpenAiService openAiService;

    @GetMapping
    public List<Event> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Event create(@RequestBody Event event) {
        return service.create(event);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<Event> search(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String location,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return service.search(title, location, start, end);
    }

//    @PostMapping("/summarizev1")
//    public Map<String, String> summarize(@RequestBody Map<String, String> body) {
//        String content = body.get("description");
//        return Map.of("summary", service.summarize(content));
//    }

    //v2
    //Kindly note that maybe it will give 400 error since i used openai on my account and i dont have subscription and i have limted calls
    @PostMapping("/summarize")
    public Map<String, String> summarize(@RequestBody Map<String, String> body) {
        String content = body.get("description");
        String summary = openAiService.summarize(content);
        return Map.of("summary", summary);
    }
}