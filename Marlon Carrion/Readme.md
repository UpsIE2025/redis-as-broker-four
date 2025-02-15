# FastAPI & Redis Message Queue

This project demonstrates a simple message queue using **FastAPI** and **Redis** in **Docker**.

## 🚀 Deployment

To start the services, run:

```bash
docker compose up -d
```

## Add message to Redis
Send a POST request to store a message in Redis.
Endpoint: POST http://127.0.0.1:8000/messages
Request Body (JSON):
```bash
{
  "content": "This is data to test"
}
```
Response:
```json lines
{
  "status": "Message added successfully"
}
```
### 📤 Consume a Message
Retrieve a message from Redis using a GET request.
Endpoint: GET http://127.0.0.1:8001/messages
Response Example:
````json lines
{
  "message": "This is data to test"
}
````
