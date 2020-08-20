package com.suchaos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
