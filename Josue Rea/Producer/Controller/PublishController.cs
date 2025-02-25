using Microsoft.AspNetCore.Mvc;
using StackExchange.Redis;

[Route("publish")]
[ApiController]
public class PublishController : ControllerBase
{
    private readonly IConnectionMultiplexer _redis;

    public PublishController()
    {
        _redis = ConnectionMultiplexer.Connect("redis:6379"); // Asegúrate de que esta dirección es correcta
    }

    [HttpPost]
    public IActionResult PublishMessage([FromBody] MessageRequest request)
    {
        try
        {
            var db = _redis.GetSubscriber();
            db.Publish("message_queue", request.Message); // Publicar el mensaje en Redis

            //Console.WriteLine($"✅ Mensaje publicado en Redis: {request.Message}");

            return Ok(new { status = "Mensaje publicado: " + request.Message });
        }
        catch (Exception ex)
        {
            Console.WriteLine($"❌ Error publicando en Redis: {ex.Message}");
            return StatusCode(500, "Error interno publicando mensaje en Redis");
        }
    }
}

public class MessageRequest
{
    public string Message { get; set; }
}
