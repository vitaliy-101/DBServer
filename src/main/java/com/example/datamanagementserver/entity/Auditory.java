package com.example.datamanagementserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "auditory", indexes = @Index(name = "idx_auditory_number", columnList = "number"))
public class Auditory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "corpus_id", nullable = false)
    private Corpus corpus;

    @OneToMany(mappedBy = "auditory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events;
}