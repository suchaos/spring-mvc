package com.suchaos.web.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * configuration
 *
 * @author suchao
 * @date 2020/8/19
 */
@Configuration
//@EnableWebMvc
public class MyWebMvcConfig implements WebMvcConfigurer {

    /*
           方法1：@Bean InternalResourceViewResolver viewResolver()
           方法2：implements WebMvcConfigurer @Override configureViewResolvers
     */

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setViewClass(JstlView.class);
//        internalResourceViewResolver.setPrefix("WEB-INF/jsp/");
//        internalResourceViewResolver.setSuffix(".jsp");
//        return internalResourceViewResolver;
//    }

//    @Override // 在 SpringBoot 中使用配置的方式进行
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setViewClass(JstlView.class);
//        internalResourceViewResolver.setPrefix("WEB-INF/jsp/");
//        internalResourceViewResolver.setSuffix(".jsp");
//        registry.viewResolver(internalResourceViewResolver);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("拦截中..." + handler);
                System.out.println(handler.getClass());
                return true;
            }
        });
    }
}
