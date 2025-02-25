var builder = WebApplication.CreateBuilder(args);

builder.WebHost.UseUrls("http://*:80"); // Permitir conexiones externas
builder.Services.AddControllers();
builder.Services.AddStackExchangeRedisCache(options =>
{
    options.Configuration = "redis:6379"; // Conexión con Redis
});

var app = builder.Build();
app.UseRouting();
app.UseEndpoints(endpoints =>
{
    endpoints.MapControllers();
});

Console.WriteLine("API Iniciada correctamente en http://*:8080");
app.Run();
