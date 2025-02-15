from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from redis import Redis
import time

app = FastAPI()

# Redis connection setup with retries
max_retries = 5
retry_count = 0
redis_conn = None

while retry_count < max_retries:
    try:
        redis_conn = Redis(host='redis', port=6379, db=0)
        redis_conn.ping()
        break
    except Exception as e:
        retry_count += 1
        time.sleep(1)
else:
    raise HTTPException(status_code=500, detail="Failed to connect to Redis")


class Message(BaseModel):
    content: str


@app.post("/messages")
def create_message(message: Message):
    try:
        redis_conn.lpush('queue', message.content)
        return {"status": "Message added", "message": message.content}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
