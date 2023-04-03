package com.acme.gym4u.fitness.domain.persistence;

import com.acme.gym4u.fitness.domain.model.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
