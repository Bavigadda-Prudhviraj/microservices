package com.prudhviraj.ecommerce.api_gateway.filters;

import com.prudhviraj.ecommerce.api_gateway.service.impl.JwtServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * A custom GatewayFilterFactory that authenticates requests by extracting the JWT token
 * from the "Authorization" header. It validates the token, retrieves the user ID, and adds
 * it to the request headers as "X-User-Id".
 */
@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private final JwtServiceImpl jwtService;

    /**
     * Constructor for AuthenticationGatewayFilterFactory.
     *
     * @param jwtService the service used to validate and parse JWT tokens.
     */
    public AuthenticationGatewayFilterFactory(JwtServiceImpl jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    /**
     * Applies the authentication filter to the request, checking for a valid JWT token
     * in the "Authorization" header and adding the user ID to the request headers.
     *
     * @param config the filter configuration.
     * @return a GatewayFilter instance that performs the authentication.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.debug("Authenticating request to {}", exchange.getRequest().getURI());

            String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (authorizationHeader == null) {
                log.warn("Authorization header is missing in the request");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try {
                String token = authorizationHeader.split("Bearer ")[1];
                Long userId = jwtService.getUserIdFromToken(token);
                log.info("Authenticated user with ID: {}", userId);

                // Add user ID to the request headers
                exchange.getRequest()
                        .mutate()
                        .header("X-User-Id", userId.toString())
                        .build();

                log.debug("Added 'X-User-Id' header to the request");
                return chain.filter(exchange);

            } catch (Exception e) {
                log.error("Failed to authenticate request: {}", e.getMessage(), e);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    /**
     * Configuration class for the authentication filter.
     */
    @Data
    public static class Config {
        private boolean isEnabled;
    }
}

