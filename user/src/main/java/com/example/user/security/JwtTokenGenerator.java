package com.example.user.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {
    private final RSAPrivateKey privateKey;

    @SneakyThrows
    public String generate(Authentication auth, long userId) {
        final var claims = new JWTClaimsSet.Builder()
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .issueTime(new Date())
                .claim("userId", userId)
                .claim("authorities", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();
        final var signer = new RSASSASigner(privateKey);
        final var signed = new SignedJWT(
                new JWSHeader(JWSAlgorithm.RS256),
                claims
        );
        signed.sign(signer);
        return signed.serialize();
    }
}
