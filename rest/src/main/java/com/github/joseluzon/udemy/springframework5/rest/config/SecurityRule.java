package com.github.joseluzon.udemy.springframework5.rest.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@PostAuthorize("hasRole('ROLE_ADMIN')")                        // run the method, but if role does not match, no response is given (forbidden)
public @interface SecurityRule {
    
}
