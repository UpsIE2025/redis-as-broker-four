package ec.edu.ups.controller;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
/**
 * @author AdrianC47
 * @date 15/2/25
 */

@Path("/messages")
public class ConsumerResource {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response receiveMessage(String message) {
        System.out.println("Consumer recibió: " + message);
        // Aquí podrías procesar o almacenar el mensaje
        return Response.ok("Mensaje procesado").build();
    }
}
