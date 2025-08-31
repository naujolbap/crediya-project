package co.com.crediya.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "routes.paths")
public record UserRoutePath(String users) {
}
