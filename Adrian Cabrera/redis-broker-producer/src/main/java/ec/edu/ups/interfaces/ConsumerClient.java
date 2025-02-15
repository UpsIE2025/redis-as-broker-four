package ec.edu.ups.interfaces;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @author AdrianC47
 * @date 15/2/25
 */
@Path("/messages")
@RegisterRestClient(configKey="consumer-api")
public interface ConsumerClient {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    String sendMessage(String message);
}
