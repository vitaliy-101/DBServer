package com.example.datamanagementserver.controller;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.event.EventRequest;
import com.example.datamanagementserver.dto.event.EventResponse;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService eventService;
    private final DataMapper dataMapper;

    @GetMapping()
    public List<EventResponse> getAll() {
        return dataMapper.convertFromEventListToResponse(eventService.getAll());
    }

    @PostMapping()
    public EventResponse create(@RequestBody EventRequest eventRequest) {
        return dataMapper.convertFromEventToResponse(eventService.create(eventRequest));
    }

    @PutMapping("/update/{id}")
    public EventResponse update(@RequestBody EventRequest eventRequest, @PathVariable Long id) {
        return dataMapper.convertFromEventToResponse(eventService.updateById(id, eventRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        eventService.deleteById(id);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll() {
        eventService.deleteAll();
        return ResponseEntity.ok("Good");
    }

//    @DeleteMapping("/delete/filter")
//    public ResponseEntity<String> deleteByFilter(@RequestBody FilterDto filterDto) {
//        eventService.deleteByFilter(filterDto);
//        return ResponseEntity.ok("Good");
//    }
    
}
