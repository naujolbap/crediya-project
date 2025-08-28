package co.com.crediya.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AuthenticationRouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(AuthenticationHandlerV1 authenticationHandlerV1) {
        return RouterFunctions
                .route()
                .path("/api/v1",
                        builder -> builder.GET("/usecase/path", authenticationHandlerV1::listenGETUseCase)
                                .POST("/usecase/otherpath", authenticationHandlerV1::listenPOSTUseCase)
                                .GET("/otherusercase/path", authenticationHandlerV1::listenGETOtherUseCase))
                .build();
    }
}
