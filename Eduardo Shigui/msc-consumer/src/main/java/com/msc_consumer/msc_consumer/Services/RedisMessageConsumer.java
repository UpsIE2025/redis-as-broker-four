package com.msc_consumer.msc_consumer.Services;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageConsumer {
    private final StringRedisTemplate stringRedisTemplate;
    public RedisMessageConsumer(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // Obtener un string de Redis
    public String obtenerString(String clave) {
        return stringRedisTemplate.opsForValue().get(clave);
    }
}
