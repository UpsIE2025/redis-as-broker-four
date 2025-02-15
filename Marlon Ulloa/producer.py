from flask import Flask, request, jsonify
import redis

app = Flask(__name__)

# Conectar con Redis
try:
    redis_client = redis.StrictRedis(host='localhost', port=6379, decode_responses=True)
    redis_client.ping()  # Verificar conexión
except redis.ConnectionError:
    redis_client = None

@app.route('/publish', methods=['POST'])
def publish_message():
    if not redis_client:
        return jsonify({"error": "Redis no disponible"}), 500

    data = request.json
    if not data or 'message' not in data:
        return jsonify({"error": "Debe enviar un mensaje"}), 400

    message = data['message']
    redis_client.rpush('messages', message)  # Agrega a la cola
    return jsonify({"success": "Mensaje publicado"}), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)