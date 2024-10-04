package com.prudhviraj.ecommerce.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalLoggingFilter implements GlobalFilter , Ordered {
    /**
     * Process the Web request and (optionally) delegate to the next {@code GatewayFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     *
     * This method is responsible for filtering HTTP requests before and after they are handled
     * by the downstream filters or handlers. It logs the request URI before the filter chain
     * is processed (pre-filter), and then logs again after the request has been processed
     * (post-filter).
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //This is preFilter
        log.info("Logging from global pre {}", exchange.getRequest().getURI());
        //Post filter
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Logging from global Post {}", exchange.getRequest().getURI());
        }));
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     *
     * This method defines the order of this filter in the filter chain. Lower values indicate
     * higher priority. For example, a filter with a lower order value will execute before
     * filters with a higher order value. Here, it returns 3, meaning it has relatively high priority.
     */
    @Override
    public int getOrder() {
        return 3;
    }
}
