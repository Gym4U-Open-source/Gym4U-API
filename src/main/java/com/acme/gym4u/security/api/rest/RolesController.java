package com.acme.gym4u.security.api.rest;

import com.acme.gym4u.security.domain.service.RoleService;
import com.acme.gym4u.security.mapping.RoleMapper;
import com.acme.gym4u.security.resource.RoleResource;
import com.acme.gym4u.security.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {
    private final RoleService roleService;

    private final RoleMapper mapper;

    public RolesController(RoleService roleService, RoleMapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Roles", description = "Get All Roles", tags = {"Roles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles returned",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResource.class)
                    ))
    })
    @GetMapping
    @PreAuthorize("hasRole('NORMAL') or hasRole('COACH')")
    public ResponseEntity<?> getAllRoles(Pageable pageable) {
        Page<RoleResource> resources = mapper.modelListToPage(roleService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }
}