package com.example.datamanagementserver.service;


import com.example.datamanagementserver.dto.event.EventRequest;
import com.example.datamanagementserver.entity.Event;
import com.example.datamanagementserver.exceptions.AlreadyBookedException;
import com.example.datamanagementserver.exceptions.CapacityException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import com.example.datamanagementserver.repository.AuditoryRepository;
import com.example.datamanagementserver.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AuditoryRepository auditoryRepository;
    private final AuditoryService auditoryService;
    private final UserService userService;

    private void checkBookTime(Event event) {
        if (eventRepository.existByBookTime(event.getStartTime(), event.getEndTime(), event.getAuditory().getId())) {
            throw new AlreadyBookedException();
        }
    }

    private void checkUserCount(Event event) {
        if (!auditoryRepository.checkUserCount(event.getUserCount(), event.getAuditory().getId())) {
            throw new CapacityException();
        }
    }

    public Event findById(Long id) {
        existById(id);
        return eventRepository.getReferenceById(id);
    }

    public Event create(EventRequest eventRequest) {
        Event event = new Event();
        convertToEventEntity(eventRequest, event);
        return eventRepository.save(event);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public void existById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundByIdException("Event", id);
        }
    }

    public Event updateById(Long id, EventRequest eventRequest) {
        Event event = findById(id);
        convertToEventEntity(eventRequest, event);
        return eventRepository.save(event);
    }

    private void convertToEventEntity(EventRequest eventRequest, Event event) {
        event.setDescription(eventRequest.getDescription());
        event.setEndTime(eventRequest.getEndTime());
        event.setStartTime(eventRequest.getStartTime());
        event.setUserCount(eventRequest.getUserCount());
        event.setUser(userService.findById(eventRequest.getUserId()));
        event.setAuditory(auditoryService.findById(eventRequest.getAuditoryId()));
        checkBookTime(event);
//        checkUserCount(event);
    }

    public void deleteById(Long id) {
        existById(id);
        eventRepository.deleteById(id);
    }

    public void deleteAll() {
        eventRepository.deleteAll();
    }
}