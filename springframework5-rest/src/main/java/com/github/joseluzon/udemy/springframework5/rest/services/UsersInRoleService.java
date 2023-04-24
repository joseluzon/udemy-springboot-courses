package com.github.joseluzon.udemy.springframework5.rest.services;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springframework5.rest.config.SecurityRule;
import com.github.joseluzon.udemy.springframework5.rest.entities.Role;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.entities.UserInRole;
import com.github.joseluzon.udemy.springframework5.rest.repositories.RolesRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersInRolesRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;

@Service
@SecurityRule
public class UsersInRoleService {
    
    private UsersInRolesRepository usersInRolesRepository;
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;

    @Autowired
    public UsersInRoleService(final UsersInRolesRepository usersInRolesRepository,
        final UsersRepository usersRepository,
        final RolesRepository rolesRepository) {
        this.usersInRolesRepository = usersInRolesRepository;
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    public UserInRole assignUserToRole(final Integer userId, final Integer roleId) {
        final var user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user id %d not found", userId));
        }
        final var role = rolesRepository.findById(roleId);
        if (role.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role id %d not found", roleId));
        }
        final var userInRole = new UserInRole();
        userInRole.setUser(user.get());
        userInRole.setRole(role.get());
        return usersInRolesRepository.save(userInRole);
    }

    public List<Role> getUserRoles(final Integer userId) {
        final var user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user id %d not found", userId));
        }
        return usersInRolesRepository.getUserRoles(userId);
    }

    // @Secured({"ROLE_ADMIN"})
    // @RolesAllowed({"ROLE_ADMIN"})
    // @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // same as above
    // @PostAuthorize("hasRole('ROLE_ADMIN')")                        // run the method, but if role does not match, no response is given (forbidden)
    // @SecurityRule
    public List<User> getRoleUsers(final Integer roleId) {
        final var role = rolesRepository.findById(roleId);
        if (role.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("role id %d not found", roleId));
        }
        return usersInRolesRepository.getRoleUsers(roleId);
    }
}
