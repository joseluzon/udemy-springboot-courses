package com.github.joseluzon.udemy.springframework5.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springframework5.rest.entities.Role;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.services.RolesService;
import com.github.joseluzon.udemy.springframework5.rest.services.UsersInRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/roles")
public class RolesController {
    
    private RolesService rolesService;
    private UsersInRoleService usersInRoleService;

    @Autowired
    public RolesController(final RolesService rolesService, final UsersInRoleService usersInRoleService) {
        this.rolesService = rolesService;
        this.usersInRoleService = usersInRoleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        final var auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Auth. Name: {}", auth.getName());
        log.info("Auth. Principal: {}", auth.getPrincipal());
        log.info("Auth. Credentials: {}", auth.getCredentials());
        log.info("Auth. Roles: {}", auth.getAuthorities().toString());
        return new ResponseEntity<>(rolesService.getRoles(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody final Role role) {
        return new ResponseEntity<>(rolesService.createRole(role), HttpStatus.OK);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable("roleId") final Integer roleId, @RequestBody final Role role) {
        return new ResponseEntity<>(rolesService.updateRole(roleId, role), HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable("roleId") final Integer roleId) {
        rolesService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{roleId}/users")
    public ResponseEntity<List<User>> getUserRoles(
        @PathVariable("roleId") final Integer roleId) {
        return new ResponseEntity<>(usersInRoleService.getRoleUsers(roleId), HttpStatus.OK);
    }
}
