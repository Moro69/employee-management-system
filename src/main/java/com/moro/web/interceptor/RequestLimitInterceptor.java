package com.moro.web.interceptor;

import com.moro.model.cache.RequestLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RequestLimitInterceptor extends HandlerInterceptorAdapter {

    private RequestLimiter limiter;

    @Autowired
    public RequestLimitInterceptor(final RequestLimiter limiter) {
        this.limiter = limiter;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {
        log.info("Received request {}", request);

        String ip = request.getRemoteAddr();

        boolean allowRequest = limiter.isPermitted(ip);

        if (!allowRequest) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        }

        return allowRequest;
    }
}
