package com.moro.web.config;

import com.moro.web.interceptor.RequestLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {

    private final RequestLimitInterceptor requestLimitInterceptor;

    @Autowired
    public WebConfig(final RequestLimitInterceptor requestLimitInterceptor) {
        this.requestLimitInterceptor = requestLimitInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(requestLimitInterceptor);
    }
}
