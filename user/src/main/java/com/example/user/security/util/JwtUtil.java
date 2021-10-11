package com.example.user.security.util;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @SneakyThrows
    @Bean
    public RSAPrivateKey rsaPrivateKey() {
        final var file = ResourceUtils.getFile("classpath:private.key");
        final var keyBytes = Base64.getMimeDecoder().decode(Files.readString(file.toPath()));
        final var keySpec = new PKCS8EncodedKeySpec(keyBytes);
        final var keyFactory = KeyFactory.getInstance("RSA");

        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

}
