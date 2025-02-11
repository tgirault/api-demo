package fr.epsitek.api.demo.application.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorMessage {
    private final String code;
    private final String message;
}
