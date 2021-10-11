package com.example.gateway.security;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtUtil {

    @SneakyThrows
    @Bean
    public RSAPublicKey rsaPublicKey() {
        final var file = ResourceUtils.getFile("classpath:public.key");
        final var keyBytes = Base64.getMimeDecoder().decode(Files.readString(file.toPath()));
        final var keySpec = new X509EncodedKeySpec(keyBytes);
        final var keyFactory = KeyFactory.getInstance("RSA");

        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }
}
