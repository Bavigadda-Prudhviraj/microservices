package com.prudhviraj.ecommerce.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingOrderSvcFilter extends AbstractGatewayFilterFactory<LoggingOrderSvcFilter.Config> {

    /**
     * Constructor to initialize the filter with its configuration class.
     * The superclass {@link AbstractGatewayFilterFactory} requires the configuration class type as a parameter.
     */
    public LoggingOrderSvcFilter() {
        // No-argument constructor for LoggingOrderSvcFilter.
        // Initializes the filter by passing the Config.class type to the superclass constructor.
        // This makes it easier to instantiate the filter without requiring external configuration.
        super(Config.class);
    }

    /**
     * Applies the filter logic to the request.
     * This method is responsible for defining the pre-processing and post-processing behavior
     * of the filter. Currently, it simply passes the request along the filter chain.
     *
     * @param config Configuration object (currently unused but allows for future customization).
     * @return {@link GatewayFilter} that processes the request and delegates to the next filter.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Pre-filter: Log the request URI before passing to the next filter
            log.info("order pre filter {}", exchange.getRequest().getURI());
            return chain.filter(exchange);  // Continue the filter chain
        };
    }

    /**
     * Configuration class for the filter.
     * It can be extended later to add custom configuration properties for the filter.
     */
    public static class Config {
        // Currently, no custom configuration properties are defined.
    }
}

