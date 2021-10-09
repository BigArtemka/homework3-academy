package com.example.user.service;

import com.example.user.entity.UserEntity;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public Map<Long, String> getIdNameMap(Set<Long> userIds) {
        final var result = repository.getAllById(userIds);
        return result.stream().collect(Collectors.toMap(UserEntity::getId, UserEntity::getName));
    }
}
