package com.example.datamanagementserver.repository;

import com.example.datamanagementserver.entity.Corpus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CorpusRepository extends JpaRepository<Corpus, Long> {
    @Transactional
    void deleteByStreet(String street);

}
