package com.github.joseluzon.udemy.springframework5.rest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.github.joseluzon.udemy.springframework5.rest.entities.Role;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.entities.UserInRole;

@Repository
public interface UsersInRolesRepository extends JpaRepository<UserInRole, Integer> {
    
    @Query("SELECT uir.role FROM UserInRole uir WHERE uir.user.id = ?1")
    public List<Role> getUserRoles(final Integer userId);

    @Query("SELECT uir.user FROM UserInRole uir WHERE uir.role.id = ?1")
    public List<User> getRoleUsers(final Integer roleId);

    public List<UserInRole> findByUser(final User user);
}
