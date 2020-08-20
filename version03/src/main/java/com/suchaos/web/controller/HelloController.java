package com.suchaos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloController
 *
 * @author suchao
 * @date 2020/8/19
 */
@Controller
public class HelloController {

    @RequestMapping("")
    public String hello(Model model) {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello2(@RequestHeader("Accept-Language") String acceptLanguage,
                         @CookieValue("JSESSIONID") String jsessionid) {
        System.out.println(acceptLanguage);
        System.out.println(jsessionid);
        return "index";
    }
}
