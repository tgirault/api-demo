package fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorMessage {
    private final String code;
    private final String message;
}
