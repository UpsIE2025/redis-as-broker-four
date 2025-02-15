import redis
import time

# Conectar con Redis
try:
    redis_client = redis.StrictRedis(host='localhost', port=6379, decode_responses=True)
    redis_client.ping()
except redis.ConnectionError:
    print("Error: Redis no disponible.")
    exit(1)

print("Esperando mensajes...")

while True:
    _, message = redis_client.blpop('messages')  # Extrae mensajes de la cola d redis
    print(f"Mensaje recibido: {message}")
    time.sleep(1)  # Tiempo de espera