# FastAPI & Redis Message Queue

This project demonstrates a simple message queue using **FastAPI** and **Redis** in **Docker**.

## Deployment

To start the services, run:

```bash
docker compose up -d
```

## Add message to Redis
#### Send a POST request to store a message in Redis.
#### Endpoint: POST http://localhost:8000/producer/send
#### Request Body (JSON):
```bash
{
	"message": "Hola"
}
```
Response:
```json lines
{
  "message": "Message stored in Redis"
}
```

## Consume a Message
#### Retrieve a message from Redis using a GET request.
#### Endpoint: GET http://localhost:8001/consumer/messages
#### Response Example:
````json lines
{
  "message": "Hola"
}
````
