package com.example.datamanagementserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "user_count")
    private Integer userCount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "auditory_id", nullable = false)
    private Auditory auditory;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
