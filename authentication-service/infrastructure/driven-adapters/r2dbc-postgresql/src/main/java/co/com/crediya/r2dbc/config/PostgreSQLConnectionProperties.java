package co.com.crediya.r2dbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "adapters.r2dbc")
public record PostgreSQLConnectionProperties(String host,
                                             Integer port,
                                             String database,
                                             String schema,
                                             String username,
                                             String password) {
}
