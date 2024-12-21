package com.example.datamanagementserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "years", indexes = @Index(name = "idx_years_year", columnList = "year"))
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "year")
    private Integer year;

    @OneToMany(mappedBy = "year")
    private Set<Group> groups;
}