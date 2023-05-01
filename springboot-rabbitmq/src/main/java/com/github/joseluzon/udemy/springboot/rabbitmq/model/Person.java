package com.github.joseluzon.udemy.springboot.rabbitmq.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person implements Serializable {
    private Long id;
    private String name;
}
