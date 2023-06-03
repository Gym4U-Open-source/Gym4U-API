package com.acme.gym4u.fitness.api.rest;

import com.acme.gym4u.fitness.domain.service.UserRoutineService;
import com.acme.gym4u.fitness.mapping.UserRoutineMapper;
import com.acme.gym4u.fitness.resource.userRoutine.CreateUserRoutineResource;
import com.acme.gym4u.fitness.resource.userRoutine.UserRoutineResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/userRoutines", produces = "application/json")
public class UserRoutineController {

    private final UserRoutineService userRoutineService;
    private final UserRoutineMapper mapper;


    public UserRoutineController(UserRoutineService userRoutineService, UserRoutineMapper mapper) {
        this.userRoutineService = userRoutineService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<UserRoutineResource> getAllUserRoutines(Pageable pageable){
        return mapper.modelListPage(userRoutineService.getAll(),pageable);
    }

    @GetMapping("{clientId}")
    public Page<UserRoutineResource> getUserRoutinesByClient(@PathVariable Long clientId, Pageable pageable){
        return mapper.modelListPage(userRoutineService.getAllByClientId(clientId), pageable);
    }

    @PostMapping
    public ResponseEntity<UserRoutineResource> createUserRoutine(@RequestBody CreateUserRoutineResource resource){
        return new ResponseEntity<>(mapper.toResource(userRoutineService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @DeleteMapping("{userRoutineId}")
    public ResponseEntity<?> deleteUserRoutine(@PathVariable Long userRoutineId){
        return userRoutineService.delete(userRoutineId);
    }
}
