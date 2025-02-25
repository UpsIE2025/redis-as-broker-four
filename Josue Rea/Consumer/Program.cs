using System.Text.Json;
using StackExchange.Redis;

class Program
{
    static void Main()
    {
        var redis = ConnectionMultiplexer.Connect("redis:6379");
        var subscriber = redis.GetSubscriber();

        Console.WriteLine("Esperando mensajes...");

        subscriber.Subscribe("message_queue", (channel, message) =>
        {
            Console.WriteLine($"✅ Recibido: {message}");
        });

        while (true) { } // Mantiene el programa en ejecución
    }
}
