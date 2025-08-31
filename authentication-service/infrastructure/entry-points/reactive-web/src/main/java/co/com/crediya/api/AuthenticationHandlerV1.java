package co.com.crediya.api;

import co.com.crediya.dto.UserRequestDTO;
import co.com.crediya.model.user.User;
import co.com.crediya.usecase.user.UserUseCase;
import co.com.crediya.uservalidation.UserValidationHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationHandlerV1 {

    private final UserValidationHelper userValidationHelper;

    private final ObjectMapper objectMapper;

    private final UserUseCase userUseCase;

    public Mono<ServerResponse> listenSaveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(userValidationHelper::validate)
                .map(dto -> objectMapper.convertValue(dto, User.class))
                .flatMap(userUseCase::saveUser)
                .flatMap(this::buildSuccessResponse);
    }

    private Mono<ServerResponse> buildSuccessResponse(User savedUser) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(savedUser);
    }
}
