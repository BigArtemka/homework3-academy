package com.example.gateway.data;

import lombok.Data;

import java.util.List;

@Data
public class JwtAuthentication {
    private final long userId;
    private final List<String> authorities;
}
