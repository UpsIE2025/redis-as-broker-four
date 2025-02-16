package com.msc_consumer.msc_consumer.Controller;

import com.msc_consumer.msc_consumer.Services.RedisMessageConsumer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class ConsumerController {
    private final RedisMessageConsumer redisService;

    public ConsumerController(RedisMessageConsumer redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/obtener/{clave}")
    public String obtener(@PathVariable String clave) {
        return redisService.obtenerString(clave);
    }
}
