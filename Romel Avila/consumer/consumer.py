from fastapi import FastAPI
import redis
import os

# Configuración de Redis
redis_host = os.getenv("REDIS_HOST", "redis")
redis_port = os.getenv("REDIS_PORT", 6379)

# Cliente Redis
r = redis.Redis(host=redis_host, port=redis_port, decode_responses=True)

app = FastAPI()

@app.get("/consumer/messages")
async def get_messages():
    """Obtiene un mensaje desde Redis"""
    message = r.brpop("messages", timeout=2)
    if message:
        return {"message": message[1]}
    return {"message": "No messages in queue"}
