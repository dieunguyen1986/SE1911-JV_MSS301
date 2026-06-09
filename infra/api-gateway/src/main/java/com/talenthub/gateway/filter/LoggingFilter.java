package com.talenthub.gateway.filter;

import com.talenthub.gateway.utils.GatewayConstraints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String header = exchange.getRequest().getHeaders().getFirst(GatewayConstraints.HEADER_CORRELATION_ID);
        String clientId = exchange.getRequest().getHeaders().getFirst(GatewayConstraints.HEADER_USER_ID);

        log.info("header={}- User Id= {}", header, clientId);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("header={}- User Id= {}", header, clientId);
        }));
    }

    @Override
    public int getOrder() {
        return GatewayConstraints.ORDER_LOGGING;
    }
}

