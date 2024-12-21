package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface YearRepository extends JpaRepository<Year, Long> {
    @Transactional
    void deleteByYear(Integer year);

    Optional<Year> findByYear(Integer year);
}
