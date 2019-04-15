package com.moro.model.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@PropertySource("classpath:requests-limit.properties")
public class RequestLimiter {

    @Value("${requests.limit}")
    private int requestLimit;

    private final RequestLimitCacher cacher;

    @Autowired
    public RequestLimiter(final RequestLimitCacher cacher) {
        this.cacher = cacher;
    }

    public boolean isPermitted(final String ip) {
        cacher.cache(ip);

        return cacher.getRequestsNumber(ip) <= requestLimit;
    }
}
