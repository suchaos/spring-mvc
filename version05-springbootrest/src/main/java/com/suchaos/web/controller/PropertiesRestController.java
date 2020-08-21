package com.suchaos.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * PropertiesRestController
 *
 * @author suchao
 * @date 2020/8/20
 */
@RestController
public class PropertiesRestController {

    @PostMapping("/add/props")
    public Properties addProperties(@RequestBody Properties properties) {
        System.out.println(properties);
        return properties;
    }
}
