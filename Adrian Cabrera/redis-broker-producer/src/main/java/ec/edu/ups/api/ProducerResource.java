package ec.edu.ups.api;

import ec.edu.ups.interfaces.ConsumerClient;
import io.quarkus.redis.client.RedisClient;  // Asegúrate de usar este import
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/produce")
public class ProducerResource {

    @Inject
    RedisClient redisClient;

    @Inject
    @RestClient
    ConsumerClient consumerClient;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response produceMessage(String message) {
        System.out.println("Productor enviando: " + message);

        // Almacenar el mensaje en Redis en una lista llamada "messages"
        redisClient.lpush(List.of("messages", message));
        System.out.println("Mensaje almacenado en Redis.");

        // Invoca al consumidor a través del REST Client
        String responseFromConsumer = consumerClient.sendMessage(message);
        return Response.ok("Mensaje enviado y almacenado en Redis. Respuesta del consumidor: " + responseFromConsumer).build();
    }
}
