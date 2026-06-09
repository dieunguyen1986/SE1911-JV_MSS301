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

import java.util.UUID;

@Component
@Slf4j
public class CorrelationIdFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Accept request
        ServerHttpRequest request = exchange.getRequest();
        String correlationId = request.getHeaders().getFirst(GatewayConstraints.HEADER_CORRELATION_ID);

        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString().substring(0, 10);
        }

        log.info("correlationId={}", correlationId);
        final String finalCorrelationId = correlationId;
        // Add the header through the builder - headers on a built request are read-only
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .header(GatewayConstraints.HEADER_CORRELATION_ID, finalCorrelationId)
                .build();

        // Register the response header before the response is committed
        exchange.getResponse().beforeCommit(() -> {
            exchange.getResponse().getHeaders()
                    .set(GatewayConstraints.HEADER_CORRELATION_ID, finalCorrelationId);
            return Mono.empty();
        });

        return chain.filter(exchange.mutate().request(newRequest).build());
    }


    @Override
    public int getOrder() {
        return GatewayConstraints.ORDER_CORRELATION;
    }
}
