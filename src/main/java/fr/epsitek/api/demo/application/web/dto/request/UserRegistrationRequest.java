package fr.epsitek.api.demo.application.web.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationRequest(@NotNull String email, @NotEmpty String firstname, @NotEmpty String lastname) {
}
