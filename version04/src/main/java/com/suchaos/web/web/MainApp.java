package com.suchaos.web.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 不推荐使用 JSP -- 直接运行报错，但可以 mvn clean package，java -jar ...war 运行
 *
 * @author suchao
 * @date 2020/8/20
 */
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
