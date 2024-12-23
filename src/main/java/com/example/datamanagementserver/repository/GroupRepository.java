package com.example.datamanagementserver.repository;

import com.example.datamanagementserver.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {


    @Transactional
    @Procedure(procedureName = "delete_group_by_number")
    void deleteGroupByNumber(@Param("number_input") Integer number);

    @Transactional
    @Procedure(procedureName = "find_group_by_number")
    void findGroupByNumber(
            @Param("number_input") Integer number,
            @Param("group_id") Long groupId,
            @Param("group_number") Integer groupNumber,
            @Param("user_count") Integer userCount,
            @Param("direction_id") Long directionId,
            @Param("year_id") Long yearId
    );

    @Transactional
    @Procedure(procedureName = "increase_user_count")
    void increaseUserCount(@Param("id_input") Long id);


    @Transactional
    @Procedure(procedureName = "decrease_user_count")
    void decreaseUserCount(@Param("id_input") Long id);
}
