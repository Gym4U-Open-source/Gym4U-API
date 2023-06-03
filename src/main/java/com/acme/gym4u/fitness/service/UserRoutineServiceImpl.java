package com.acme.gym4u.fitness.service;

import com.acme.gym4u.fitness.domain.model.entity.UserRoutine;
import com.acme.gym4u.fitness.domain.model.entity.Workout;
import com.acme.gym4u.fitness.domain.persistence.RoutineRepository;
import com.acme.gym4u.fitness.domain.persistence.UserRoutineRepository;
import com.acme.gym4u.fitness.domain.persistence.WorkoutRepository;
import com.acme.gym4u.fitness.domain.service.UserRoutineService;
import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class UserRoutineServiceImpl implements UserRoutineService {

    private static final String ENTITY = "UserRoutine";
    private final UserRoutineRepository userRoutineRepository;
    private final RoutineRepository routineRepository;
    private final WorkoutRepository workoutRepository;
    private final Validator validator;
    private UserContextFacade userContextFacade;


    public UserRoutineServiceImpl(UserRoutineRepository userRoutineRepository, RoutineRepository routineRepository, WorkoutRepository workoutRepository, Validator validator, UserContextFacade userContextFacade) {
        this.userRoutineRepository = userRoutineRepository;
        this.routineRepository = routineRepository;
        this.workoutRepository = workoutRepository;
        this.validator = validator;
        this.userContextFacade = userContextFacade;
    }

    @Override
    public List<UserRoutine> getAll() {
        return userRoutineRepository.findAll();
    }

    @Override
    public List<UserRoutine> getAllByClientId(Long clientId) {
        return userRoutineRepository.findAllByClientId(clientId);
    }

    @Override
    public Page<UserRoutine> getAll(Pageable pageable) {
        return userRoutineRepository.findAll(pageable);
    }

    @Override
    public UserRoutine create(UserRoutine userRoutine) {

        Set<ConstraintViolation<UserRoutine>> violations = validator.validate(userRoutine);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        //User user = userContextFacade.findByUserToken().orElseThrow(()-> new ResourceNotFoundException("User not found"));
        //Routine routine =  routineRepository.findById(userRoutine.getRoutine().getId()).orElseThrow(()-> new ResourceNotFoundException("Routine not found"));
        Workout workout = workoutRepository.findById(userRoutine.getWorkout().getId()).orElseThrow(()-> new ResourceNotFoundException("Workout not found"));
        User client = userContextFacade.findByUserId(userRoutine.getClient().getId()).orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        UserRoutine newUserRoutine = new UserRoutine();
        //newUserRoutine.setUser(user);
        newUserRoutine.setWorkout(workout);
        newUserRoutine.setClient(client);

        return userRoutineRepository.save(newUserRoutine);
    }

    @Override
    public ResponseEntity<?> delete(Long userRoutineId) {

        return userRoutineRepository.findById(userRoutineId).map(userRoutine -> {
            userRoutineRepository.delete(userRoutine);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, userRoutineId));
    }
}
