package com.suchaos.web.http.converter.properties;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 自定义的 {@link org.springframework.http.converter.HttpMessageConverter}
 * <p>
 * 只是来学习 Spring MVC　的流程的，平时一般不会自定义这个的
 *
 * @author suchao
 * @date 2020/8/21
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        // 设置支持的媒体类型
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream messageBody = outputMessage.getBody();
        Charset charset = outputMessage.getHeaders().getContentType().getCharset();
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }

        OutputStreamWriter writer = new OutputStreamWriter(messageBody, charset);
        properties.store(writer, "from PropertiesHttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream messageBody = inputMessage.getBody();
        Charset acceptCharset = inputMessage.getHeaders().getContentType().getCharset();
        if (acceptCharset == null) {
            acceptCharset = StandardCharsets.UTF_8;
        }
        Properties properties = new Properties();
        properties.load(new InputStreamReader(messageBody, acceptCharset));

        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }
}
