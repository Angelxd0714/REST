# Proyecto REST API con Docker y AZURE APP WEB

## Descripción

Este proyecto es una aplicación REST API desplegada utilizando Docker y alojada en una instancia appWEB de AZURE. La aplicación está configurada para ejecutarse con una base de datos MySQL y está expuesta en el puerto 8060.

## Estructura del Proyecto

- `compose.yml`: Archivo de configuración para Docker Compose.
- `Dockerfile`: Archivo de configuración para construir la imagen Docker de la aplicación.
- `src/`: Código fuente de la aplicación.

## Pasos para Desplegar

### 1. Preparación del Entorno Local

1. **Instalación de Docker y Docker Compose**

   Asegúrate de que Docker y Docker Compose están instalados en tu máquina local.

   ```bash
   sudo apt-get update
   sudo apt-get install docker.io docker-compose
