package com.github.joseluzon.udemy.springframework5.rest.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuditDetails {
    private String createdByUser;
    private String userRole;
}
