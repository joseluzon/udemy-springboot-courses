package com.github.joseluzon.udemy.springframework5.core.di.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class ScopeServicePrototype {
    
}
