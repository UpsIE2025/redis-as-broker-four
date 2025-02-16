from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import redis
import os

# Configuración de Redis
redis_host = os.getenv("REDIS_HOST", "redis")
redis_port = os.getenv("REDIS_PORT", 6379)

# Cliente Redis
r = redis.Redis(host=redis_host, port=redis_port, decode_responses=True)

app = FastAPI()

class Message(BaseModel):
    message: str

@app.post("/producer/send")
async def send_message(data: Message):
    try:
        r.lpush("messages", data.message)
        return {"message": "Message stored in Redis"}
    except redis.exceptions.ConnectionError:
        raise HTTPException(status_code=500, detail="Redis not available")