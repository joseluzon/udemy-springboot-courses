package com.github.joseluzon.udemy.springframework5.dependencyinjection.qualifiers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Animal {
    @Getter
    private final String name;
    @Getter
    private final Integer age;
}
