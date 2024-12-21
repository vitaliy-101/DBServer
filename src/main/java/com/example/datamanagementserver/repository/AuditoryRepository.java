package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuditoryRepository extends JpaRepository<Auditory, Long> {
    @Transactional
    void deleteByNumber(Integer number);

    @Query("SELECT COUNT(ad) > 0 FROM Auditory ad WHERE ad.id = :auditoryId AND ad.capacity >= :userCount")
    boolean checkUserCount(Integer userCount, Long auditoryId);

}
