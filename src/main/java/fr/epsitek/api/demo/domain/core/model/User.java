package fr.epsitek.api.demo.domain.core.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
}
