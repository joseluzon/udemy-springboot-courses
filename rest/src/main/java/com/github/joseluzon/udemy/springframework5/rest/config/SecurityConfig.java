package com.github.joseluzon.udemy.springframework5.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/users/**").hasRole("ADMIN")
            .and().authorizeRequests()
                .antMatchers("/roles/**").authenticated()
            .and().authorizeRequests()
                .anyRequest().authenticated()
            .and().httpBasic();
        return http.build();
    }

    // Quick and easy way for testing porpouses
    // @Bean
    // public UserDetailsService users() {
    //     final var admin = User.builder().username("admin").password(encoder().encode("password"))
    //             .roles("ADMIN").build();
    //     final var user = User.builder().username("user").password(encoder().encode("password"))
    //             .roles("USER").build();
    //     return new InMemoryUserDetailsManager(admin, user);
    // }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
