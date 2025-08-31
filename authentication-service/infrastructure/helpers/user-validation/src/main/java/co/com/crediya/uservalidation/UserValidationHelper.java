package co.com.crediya.uservalidation;

import co.com.crediya.api.dto.UserRequestDTO;
import co.com.crediya.uservalidation.exception.UserValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserValidationHelper {

    private final Validator validator;

    public Mono<UserRequestDTO> validate(UserRequestDTO userRequest) {
        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequest);

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            return Mono.error(new UserValidationException(errorMessage));
        }

        return Mono.just(userRequest);
    }
}
