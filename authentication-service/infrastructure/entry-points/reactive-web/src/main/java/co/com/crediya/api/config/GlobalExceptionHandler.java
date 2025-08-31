package co.com.crediya.api.config;

import co.com.crediya.api.dto.ErrorResponseDTO;
import co.com.crediya.r2dbc.exception.UserAlreadyExistsException;
import co.com.crediya.uservalidation.exception.UserValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserValidationException.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleUserValidationException(UserValidationException ex) {
        log.error("Validation error: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("VALIDATION_ERROR", ex.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error("User already exists error: {}", ex.getMessage(), ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("USER_ALREADY_EXISTS", ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleGenericException(Exception ex) {
        log.error("Internal server error: ", ex);

        ErrorResponseDTO errorResponse = new ErrorResponseDTO("INTERNAL_ERROR", "Ha ocurrido un error interno");
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }
}
