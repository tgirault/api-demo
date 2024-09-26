package fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
}
