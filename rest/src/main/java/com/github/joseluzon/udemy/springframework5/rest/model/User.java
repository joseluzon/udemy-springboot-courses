package com.github.joseluzon.udemy.springframework5.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class User {
    private String nickName;
    private String userName;
    private String password;
}
