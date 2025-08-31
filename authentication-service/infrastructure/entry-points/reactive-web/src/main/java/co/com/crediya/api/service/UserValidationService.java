package co.com.crediya.api.service;

import co.com.crediya.api.dto.UserRequestDTO;
import co.com.crediya.api.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserValidationService {

    private final Validator validator;

    public Mono<UserRequestDTO> validate(UserRequestDTO userRequest) {
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequest);

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Mono.error(new ValidationException(errorMessage));
        }

        return Mono.just(userRequest);
    }
}
