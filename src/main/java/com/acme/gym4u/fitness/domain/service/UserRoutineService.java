package com.acme.gym4u.fitness.domain.service;

import com.acme.gym4u.fitness.domain.model.entity.Routine;
import com.acme.gym4u.fitness.domain.model.entity.UserRoutine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRoutineService {
    List<UserRoutine> getAll();
    List<UserRoutine> getAllByClientId(Long clientId);
    Page<UserRoutine> getAll(Pageable pageable);

    UserRoutine create(UserRoutine userRoutine);
    ResponseEntity<?> delete(Long userRoutineId);
}
