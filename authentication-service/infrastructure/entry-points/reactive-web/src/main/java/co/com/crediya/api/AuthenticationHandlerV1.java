package co.com.crediya.api;

import co.com.crediya.api.dto.ErrorResponseDTO;
import co.com.crediya.api.dto.UserRequestDTO;
import co.com.crediya.api.exception.ValidationException;
import co.com.crediya.api.service.UserValidationService;
import co.com.crediya.model.user.User;
import co.com.crediya.usecase.user.UserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationHandlerV1 {

    private final UserUseCase userUseCase;

    private final ObjectMapper objectMapper;
    
    private final UserValidationService validationService;

    public Mono<ServerResponse> listenSaveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(validationService::validate)
                .map(dto -> objectMapper.convertValue(dto, User.class))
                .flatMap(userUseCase::saveUser)
                .flatMap(this::buildSuccessResponse)
                .onErrorResume(this::handleError);
    }

    private Mono<ServerResponse> buildSuccessResponse(User savedUser) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(savedUser);
    }

    private Mono<ServerResponse> handleError(Throwable error) {
        if (error instanceof ValidationException) {
            return ServerResponse.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new ErrorResponseDTO("VALIDATION_ERROR", error.getMessage()));
        }

        // Manejar otros tipos de errores
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ErrorResponseDTO("INTERNAL_ERROR", "Error interno del servidor"));
    }
}
