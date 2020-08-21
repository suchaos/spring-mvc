package com.suchaos.web.controller;

import com.suchaos.web.domain.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserRestController
 *
 * @author suchao
 * @date 2020/8/20
 */
@RestController
public class UserRestController {

    @PostMapping(value = "/echo/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User echo(@RequestBody User user) {
        return user;
    }
}
