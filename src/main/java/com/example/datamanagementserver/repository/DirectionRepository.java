package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Transactional
    void deleteByName(String name);

    Optional<Direction> findByName(String name);
}
