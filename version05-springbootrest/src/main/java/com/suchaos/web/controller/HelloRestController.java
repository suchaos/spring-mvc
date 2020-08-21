package com.suchaos.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author suchao
 * @date 2020/8/20
 */
@RestController
public class HelloRestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, REST";
    }
}
