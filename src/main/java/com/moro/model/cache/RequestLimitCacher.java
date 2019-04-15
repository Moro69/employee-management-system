package com.moro.model.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@PropertySource("classpath:requests-limit.properties")
@Slf4j
public class RequestLimitCacher {

    private static final long MILLIS_IN_MINUTE = 60000L;

    private Map<String, Smth> cache;

    @Autowired
    public RequestLimitCacher(@Value("${requests.cache.capacity}")
                                          final int cacheCapacity) {
        cache = new RequestLimitCache<>(cacheCapacity);
    }

    public void cache(final String ip) {
        log.info("Caching {}", ip);

        if (cache.containsKey(ip)) {
            if (!isPeriodExpired(cache.get(ip))) {
                cache.get(ip).incrRequestsNumber();
            } else {
                cache.get(ip).setFirstRequestDate(new Date());
                cache.get(ip).setRequestsNumber(1);
            }
        } else {
            cache.put(ip, new Smth(new Date(), 1));
        }
    }

    private boolean isPeriodExpired(final Smth smth) {
        return new Date().getTime() - smth.getFirstRequestDate().getTime() > MILLIS_IN_MINUTE;
    }

    public int getRequestsNumber(final String ip) {
        return cache.get(ip).getRequestsNumber();
    }
}
