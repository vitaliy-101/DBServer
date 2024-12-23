package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

    @Transactional
    @Procedure(procedureName = "delete_direction_by_name")
    void deleteByName(@Param("direction_name") String name);

    @Transactional
    @Procedure(procedureName = "find_direction_by_name")
    Optional<Direction> findByName(@Param("direction_name") String name);
}
