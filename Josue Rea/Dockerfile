# Etapa de construcción
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /app

# Copiamos los archivos del proyecto
COPY Consumer/ Consumer/
COPY Producer/ Producer/

# Restauramos y compilamos Producer
WORKDIR /app/Producer
RUN dotnet restore
RUN dotnet publish -c Release -o /app/out-producer

# Restauramos y compilamos Consumer
WORKDIR /app/Consumer
RUN dotnet restore
RUN dotnet publish -c Release -o /app/out-consumer

# Imagen final para ejecutar las aplicaciones
FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS runtime
WORKDIR /app

# Copiamos las aplicaciones compiladas
COPY --from=build /app/out-producer ./Producer/
COPY --from=build /app/out-consumer ./Consumer/

# Ejecutamos ambos servicios en paralelo
CMD ["sh", "-c", "dotnet Producer/Producer.dll & dotnet Consumer/Consumer.dll; wait"]

