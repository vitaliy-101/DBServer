package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT COUNT(ev) > 0 FROM Event ev WHERE (ev.startTime <= :startTime AND :startTime <= ev.endTime) OR " +
            "(:endTime >= ev.startTime AND :endTime <= ev.endTime) AND ev.auditory.id = :auditoryId")
    boolean existByBookTime(LocalDateTime startTime, LocalDateTime endTime, Long auditoryId);

}