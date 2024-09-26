package fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRequest(@NotNull String email, @NotEmpty String firstname, @NotEmpty String lastname) {
}
