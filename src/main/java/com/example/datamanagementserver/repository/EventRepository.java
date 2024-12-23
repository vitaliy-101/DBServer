package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Transactional
    @Procedure(procedureName = "check_event_time_overlap")
    boolean existByBookTime(@Param("start_time_input") LocalDateTime startTime,
                            @Param("end_time_input") LocalDateTime endTime,
                            @Param("auditory_id_input") Long auditoryId);
}
