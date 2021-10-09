package com.example.aggregator.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

@FeignClient(value = "user")
public interface UserClient {
    @PostMapping
    Map<Long, String> getIdNameMap(@RequestBody Set<Long> userIds);
}
