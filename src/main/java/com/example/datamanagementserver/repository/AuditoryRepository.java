package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuditoryRepository extends JpaRepository<Auditory, Long> {


    @Modifying
    @Transactional
    @Procedure(procedureName = "delete_auditory_by_number")
    void deleteByNumber(@Param("auditory_number") Integer number);


    @Transactional
    @Procedure(procedureName = "check_user_count")
    boolean checkUserCount(Integer userCount,
                           Long auditoryId);
}