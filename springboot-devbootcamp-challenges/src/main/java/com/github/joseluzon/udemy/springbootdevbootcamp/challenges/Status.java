package com.github.joseluzon.udemy.springbootdevbootcamp.challenges;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Status {
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    private final String displayText;
}
