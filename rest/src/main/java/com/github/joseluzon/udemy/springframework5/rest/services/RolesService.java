package com.github.joseluzon.udemy.springframework5.rest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.joseluzon.udemy.springframework5.rest.entities.Role;
import com.github.joseluzon.udemy.springframework5.rest.kafka.AuditDetails;
import com.github.joseluzon.udemy.springframework5.rest.repositories.RolesRepository;

@Service
public class RolesService {
    
    private RolesRepository roleRepository;

    private KafkaTemplate<Integer, String> kafkaTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public RolesService(final RolesRepository roleRepository, final KafkaTemplate<Integer, String> kafkaTemplate) {
        this.roleRepository = roleRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(final Role role) {
        final var roleCreated = roleRepository.save(role);
        final var auth = SecurityContextHolder.getContext().getAuthentication();
        final var auditDetails = new AuditDetails(auth.getName(), role.getName());
        try {
            kafkaTemplate.send("test-topic", mapper.writeValueAsString(auditDetails));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error sending audit details", e);
        }
        return roleCreated;
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
