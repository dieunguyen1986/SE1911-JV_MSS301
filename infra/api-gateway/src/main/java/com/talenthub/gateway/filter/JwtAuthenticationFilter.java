package com.talenthub.gateway.filter;

import com.talenthub.gateway.utils.GatewayConstraints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest muteRequest = exchange.getRequest().mutate().headers((httpHeaders) -> {
            httpHeaders.set(GatewayConstraints.HEADER_USER_ID, "10");
            httpHeaders.set(GatewayConstraints.HEADER_EMAIL, "rec@example.com");
            httpHeaders.set(GatewayConstraints.HEADER_ROLE_NAME, "['ADMIN', 'REC']");

        }).build();

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return GatewayConstraints.ORDER_JWT_AUTH;
    }
}
