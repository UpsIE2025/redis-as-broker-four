package ec.edu.ups.controller;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class MessagePoller {

    @Inject
    RedisClient redisClient;

    // Este método se ejecutará cada 10 segundos
    @Scheduled(every="10s")
    public void pollMessages() {
        Response messageResponse = redisClient.rpop("messages");
        while (messageResponse != null) {
            String message = messageResponse.toString();
            System.out.println("Consumidor procesando mensaje desde Redis: " + message);
            // Aquí puedes agregar la lógica para procesar el mensaje (p.ej., almacenarlo en una base de datos)
            messageResponse = redisClient.rpop("messages");
        }
    }
}
