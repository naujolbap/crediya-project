package co.com.crediya.api;

import co.com.crediya.api.config.UserRoutePath;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@RequiredArgsConstructor
public class AuthenticationRouterRest {

    private final UserRoutePath userRoutePath;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(AuthenticationHandlerV1 authenticationHandlerV1) {
        return RouterFunctions.route(POST(userRoutePath.users()), authenticationHandlerV1::listenSaveUser);
    }
}
