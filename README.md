# Software para Gestión de Biblioteca

Este proyecto es una aplicación de escritorio para gestionar una biblioteca. Componentes principales:

- Lógica de la aplicación en **Java 21** con **Spring Boot**
- Consultas a la base de datos con **Spring Data JPA** y **jOOQ** 
- Frontend con **React** embedido en una ventana de **JavaFX**
- Base de datos **PostgreSQL** administrada con **Flyway**

## Requisitos previos

Antes de levantar el proyecto, asegúrate de tener instalados:

1. **Java JDK 21**
    - Configura `JAVA_HOME` correctamente.
2. **Maven** (o Maven Wrapper `./mvnw`)
3. **Node.js y npm** (para compilar el frontend React)
4. **Docker y Docker Compose** (para la base de datos y Flyway

## Pasos para levantar el proyecto

### 1️⃣ Compilar el frontend React

El frontend debe compilarse y copiarse a los recursos de Java antes de ejecutar el backend:

```bash
./build-frontend.sh
```

- Este script compila el código React.
- Copia los archivos generados a `/src/main/resources`.
- Para ver los cambios hechos en React en la ventana de JavaFX, **debes volver a ejecutar este script**.

#### Nota: Para desarrollar sin que el proceso sea tedioso, el frontend se desarrolla de manera separada en un navegador con todo el tooling de React con los comandos:

```bash
 cd frontend
 npm run dev
```

### 2️⃣ Levantar PostgreSQL y Flyway con Docker Compose

El archivo `dev.docker-compose.yml` levanta la base de datos y aplica las migraciones:

```bash
docker compose -f dev.docker-compose.yml up
```

- Levanta **PostgreSQL** para desarrollo.
- Ejecuta **Flyway**, aplicando las migraciones que se encuentran en `./migrations`.

> **Nota:** Mantén Docker Compose corriendo mientras desarrollas, ya que la aplicación necesita conectarse a la base de datos.


###  3️⃣ Generar código para jOOQ

El proyecto utiliza jOOQ para algunas consultas a la base de datos. Debes generar las clases de jOOQ para poder compilar el proyecto:

#### Opción A: Usando Maven

```bash
mvn generate-sources
```

o con Maven Wrapper:

```bash
./mvnw generate-sources
```

- Esto generará las clases necesarias en `target/generated-sources/jooq`.

#### Opción B: Usando IntelliJ IDEA

1. Ve a `View > Tool Windows > Maven > Plugins > jooq-codegen > jooq-codegen:generate`.
2. Ejecuta la generación.
3. Las clases se crearán en la carpeta configurada (`target/generated-sources/jooq`).

> **Importante:** Cada vez que cambies la estructura de la base de datos, **regenera jOOQ** antes de compilar.

### 4️⃣ Configurar perfil de Spring y ejecutar la aplicación

Para poder desarrollar, la aplicación requiere que la variable de entorno del perfil Spring esté en `dev`. En IntelliJ haz lo siguiente:

1. Ve a `Run > Edit Configurations > Application > App > Environment Variables`. 
2. Agrega una nueva variable de entorno `SPRING_PROFILES_ACTIVE=dev`

> **¿Por qué esto es necesario?:** Porque en modo "dev" la aplicación inserta datos de prueba en las tablas de la base de datos

## Resumen

1. Compilar frontend (`./build-frontend.sh`)
2. Levantar PostgreSQL y Flyway (`docker-compose -f dev.docker-compose.yml up`)
3. Generar código de jOOQ (`mvn generate-sources` o IntelliJ)
4. Configurar `SPRING_PROFILES_ACTIVE=dev` y ejecutar la app

> Si cualquiera de estos pasos falla o se omite, la aplicación **no se levantará correctamente**.

### Notas y recomendaciones


- Desarrolla el frontend en el navegador de manera normal con `npm run dev` en el directorio `/frontend`. La app NO se conecta a la base de datos en este caso, lo que te permite desarrollar libremente.
- Cada vez que quieras ver los cambios al frontend en la ventana de Java, ejecuta `./build-frontend.sh`.
- Mantén **Docker Compose corriendo** mientras trabajas con la app.
- Si necesitas hacer cambios en la estructura de la base de datos, evalúa si es mejor alterar un archivo en ./migrations ya existente, o si necesitas crear uno nuevo. Depende del caso y del tamaño de la modificación. 
- Recuerda que para un cambio completo de la estructura de la base de datos debes correr las migraciones de nuevo en tu instancia de Postgres corriendo en Docker Compose y generar el código de jOOQ de nuevo.
