package com.acme.gym4u.fitness.domain.persistence;

import com.acme.gym4u.fitness.domain.model.entity.UserRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoutineRepository extends JpaRepository<UserRoutine, Long> {
    /*
    @Query("SELECT ur FROM UserRoutine ur WHERE ur.client.id = :clientId")
    List<UserRoutine> findAllByClientId(@Param("clientId") Long clientId);

     */

    List<UserRoutine> findAllByClientId(Long clientId);
}

