package com.example.datamanagementserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "corpus", indexes = @Index(name = "idx_corpus_street", columnList = "street"))
public class Corpus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "town")
    private String town;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @OneToMany(mappedBy = "corpus", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Auditory> auditoriums;
}