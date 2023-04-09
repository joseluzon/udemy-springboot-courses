package com.github.joseluzon.udemy.springframework5.rest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springframework5.rest.entities.Role;
import com.github.joseluzon.udemy.springframework5.rest.repositories.RolesRepository;

@Service
public class RolesService {
    
    private RolesRepository roleRepository;

    @Autowired
    public RolesService(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(final Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(final Integer roleId, final Role role) {
        final var roleToUpdate = roleRepository.findById(roleId);
        if (roleToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d not found", roleId));
        }
        return roleRepository.save(role);
    }

    public void deleteRole(final Integer roleId) {
        final var roleToUpdate = roleRepository.findById(roleId);
        if (roleToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d not found", roleId));
        }
        roleRepository.deleteById(roleId);
    }
    
}
