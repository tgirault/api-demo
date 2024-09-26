package fr.epsitek.api.demo.infrastructure.adapters.input.rest.handler;

import fr.epsitek.api.demo.domain.core.exception.BusinessException;
import fr.epsitek.api.demo.domain.core.exception.TechnicalException;
import fr.epsitek.api.demo.domain.core.exception.UserAlreadyExistsException;
import fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder().code("FUNC-ERR-USER-001").message(ex.getMessage()).build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder().code("FUNCTIONAL_ERROR").message(ex.getMessage()).build();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<?> handleTechnicalException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder().code("TECHNICAL_ERROR").message(ex.getMessage()).build();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder().code("GLOBAL_ERROR").message(ex.getMessage()).build();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
