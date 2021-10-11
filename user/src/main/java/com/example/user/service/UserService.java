package com.example.user.service;

import com.example.user.dto.TokenResponseDto;
import com.example.user.entity.UserEntity;
import com.example.user.exception.UserNotFoundException;
import com.example.user.repository.UserRepository;
import com.example.user.security.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final JwtTokenGenerator tokenGenerator;

    public Map<Long, String> getIdNameMap(Set<Long> userIds) {
        final var result = repository.getAllById(userIds);
        return result.stream().collect(Collectors.toMap(UserEntity::getId, UserEntity::getName));
    }

    public TokenResponseDto getToken(Authentication authentication) {
        final var userId = repository.getByName(authentication.getName())
                .orElseThrow(UserNotFoundException::new).getId();
        return new TokenResponseDto(tokenGenerator.generate(authentication, userId));
    }
}
