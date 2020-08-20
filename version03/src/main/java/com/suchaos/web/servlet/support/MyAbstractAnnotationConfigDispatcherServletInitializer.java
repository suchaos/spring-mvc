package com.suchaos.web.servlet.support;

import com.suchaos.web.config.DispatcherServletConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring MVC 自动装配默认实现
 *
 * @author suchao
 * @date 2020/8/19
 */
public class MyAbstractAnnotationConfigDispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DispatcherServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
