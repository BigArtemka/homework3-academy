package com.example.gateway.filter;

import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {
    private final RSAPublicKey publicKey;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if ("/token".equals(exchange.getRequest().getPath().toString()))
            return chain.filter(exchange);
        final var authorization = exchange.getRequest().getHeaders().get("Authorization");
        final var response = exchange.getResponse();
        if (authorization == null || authorization.isEmpty()) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        final var token = authorization.stream().filter(s -> s.startsWith("Bearer ")).map(s -> s.substring(7))
                .findFirst().orElse("");
        if (token.isEmpty()) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        final var deserialized = SignedJWT.parse(token);
        final var verifier = new RSASSAVerifier(publicKey);
        if (!deserialized.verify(verifier) ||
                ((Date) deserialized.getJWTClaimsSet().getClaim("exp")).getTime() < new Date().getTime()) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        final var request = exchange.getRequest().mutate()
                .header("Authn",
                        deserialized.getPayload().toBase64URL().toString()).build();
        return chain.filter(exchange.mutate().request(request).build());
    }
}
