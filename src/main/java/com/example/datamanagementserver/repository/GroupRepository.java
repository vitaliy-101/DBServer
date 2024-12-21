package com.example.datamanagementserver.repository;

import com.example.datamanagementserver.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Transactional
    void deleteByNumber(Integer number);

    Optional<Group> findByNumber(Integer number);

    @Transactional
    @Modifying
    @Query("UPDATE Group gr SET gr.userCount = gr.userCount + 1" +
            " WHERE gr.id = :id")
    void increaseUserCount(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Group gr SET gr.userCount = gr.userCount - 1" +
            " WHERE gr.id = :id")
    void decreaseUserCount(Long id);
}
