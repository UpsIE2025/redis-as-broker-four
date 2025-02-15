import time
from typing import Union

import redis
from fastapi import FastAPI
from redis import Redis
from starlette.exceptions import HTTPException

app = FastAPI()

# Redis connection with retries
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


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/messages")
def get_message():
    try:
        message = redis_conn.brpop(['queue'], timeout=5)
        if message:
            return {"message": message[1].decode('utf-8')}
        return {"message": "No messages available"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
