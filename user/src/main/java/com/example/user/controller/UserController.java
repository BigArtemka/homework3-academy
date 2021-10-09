package com.example.user.controller;


import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Set;

@RestController
@Transactional
@RequiredArgsConstructor
public class UserController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final UserService service;

    @PostMapping
    Map<Long, String> getIdNameMap(@RequestBody Set<Long> userIds) {
        logger.info("getUsersById");
        return service.getIdNameMap(userIds);
    }
}
