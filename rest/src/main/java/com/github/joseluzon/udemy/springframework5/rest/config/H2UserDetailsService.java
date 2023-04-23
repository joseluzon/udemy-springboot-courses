package com.github.joseluzon.udemy.springframework5.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersInRolesRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;

@Service
public class H2UserDetailsService implements UserDetailsService {

    private UsersRepository userRepository;
    private UsersInRolesRepository usersInRolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public H2UserDetailsService(final UsersRepository userRepository, final UsersInRolesRepository usersInRolesRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.usersInRolesRepository = usersInRolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("username '%s' not found", username));
        }
        final var user = userOptional.get();
        final var userInRoles = usersInRolesRepository.findByUser(user);
        final var roles = userInRoles.stream().map(role -> role.getRole().getName()).toArray(String[]::new);
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
    }
    
}
