package fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.response;

import lombok.Data;

@Data
public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;
}
