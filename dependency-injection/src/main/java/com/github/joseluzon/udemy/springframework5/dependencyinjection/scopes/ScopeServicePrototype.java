package com.github.joseluzon.udemy.springframework5.dependencyinjection.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class ScopeServicePrototype {
    
}
