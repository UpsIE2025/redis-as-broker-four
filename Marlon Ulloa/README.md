
# Instrucciones para Ejecutar la Aplicación

Para ejecutar la aplicación, sigue los pasos a continuación:

## 1. Levantar Redis en Docker con Docker Compose

Ejecuta el siguiente comando para levantar Redis usando Docker Compose:

```bash
docker compose up --build -d
```

## 2. Ejecutar el Script de Python del Producer

Una vez que Redis esté levantado, ejecuta el script del productor:

```bash
python producer.py
```

## 3. Ejecutar el Script de Python del Consumer

Luego, ejecuta el script del consumidor:

```bash
python consumer.py
```

## 4. Probar la Funcionalidad con Postman

Para probar la funcionalidad, usa Postman con los siguientes parámetros:

- **URL**: `http://localhost:5000/publish`
- **Método**: `POST`
- **Body (JSON)**:

```json
{
  "message": "Envío de Mensaje 1"
}
```
