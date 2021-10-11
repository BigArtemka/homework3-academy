package com.example.producer.data;

import lombok.Data;

import java.util.List;

@Data
public class JwtAuthentication {
    private final long exp, iat;
    private final long userId;
    private final List<String> authorities;
}
