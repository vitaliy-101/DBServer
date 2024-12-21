package com.example.datamanagementserver.repository;


import com.example.datamanagementserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    void deleteByEmail(String email);
    @Transactional
    void deleteByFirstname(String firstname);
    @Transactional
    void deleteBySurname(String surname);
    @Transactional
    void deleteByPatronymic(String patronymic);

    Optional<User> findByEmail(String email);
    Optional<User> findByFirstname(String firstname);
    Optional<User> findBySurname(String surname);
    Optional<User> findByPatronymic(String patronymic);

    Optional<User> findByFirstnameAndSurnameAndPatronymic(String firstname, String surname, String patronymic);

    boolean existsByPasswordAndEmail(String password, String email);


}
