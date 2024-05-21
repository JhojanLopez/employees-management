# Gestión de Empleados Aplicación WEB

## Descripción

Este es un proyecto de gestión de empleados y departamentos realizado con Jakarta EE 9, Java 11 y PrimeFaces 11. El proyecto implementa operaciones CRUD (Crear, Leer, Actualizar y Eliminar) para las entidades **Empleado** y **Departamento**, y utiliza AJAX para mejorar la experiencia de usuario con actualizaciones parciales de la página sin necesidad de recargarla completamente.

## Tecnologías Utilizadas

- **Jakarta EE 9**: Plataforma utilizada para el desarrollo de aplicaciones empresariales.
- **Java 11**: Versión del lenguaje de programación Java utilizada.
- **PrimeFaces 11**: Biblioteca de componentes UI para JavaServer Faces (JSF) que facilita la creación de interfaces de usuario modernas.
- **AJAX**: Técnica utilizada para realizar peticiones asincrónicas al servidor y actualizar la interfaz de usuario sin recargar la página completa.
- **Bootstrap**: Biblioteca CSS utilizada para el diseño responsivo y moderno de la interfaz de usuario.
- **JBoss**: Servidor de aplicaciones utilizado para desplegar la arquitectura multinivel del proyecto.
- **H2 Database**: Base de datos utilizada para almacenar la información de empleados y departamentos.

## Características

- **CRUD de Empleados**: Permite crear, leer, actualizar y eliminar registros de empleados.
- **CRUD de Departamentos**: Permite crear, leer, actualizar y eliminar registros de departamentos.
- **Interfaz de Usuario Dinámica**: Gracias al uso de PrimeFaces, AJAX y Bootstrap, la interfaz es interactiva y proporciona una mejor experiencia de usuario.
- **Arquitectura Multinivel**: Implementada con el servidor de aplicaciones JBoss para una mejor separación de preocupaciones y escalabilidad.
- **Base de Datos en Memoria**: Uso de H2 Database para facilitar el desarrollo y pruebas rápidas.

## Requisitos Previos

Para ejecutar este proyecto, necesitas tener instalados los siguientes componentes:

- JDK 11
- Servidor Wildfly version 31

## Configuración y Despliegue

1. **Descargar y Configurar WildFly 31**:
    - Descarga WildFly 31 desde la [página oficial](https://www.wildfly.org/downloads/).
    - Extrae el archivo ZIP descargado en una ubicación de tu preferencia.
    - Navega a la carpeta `bin` dentro del directorio donde extrajiste WildFly.

2. **Iniciar el Servidor WildFly**:
    - En Windows, ejecuta el comando:
      ```bash
      .\standalone.bat
      ```
   - En Linux/Mac, ejecuta el siguiente comando para iniciar el servidor:
     ```bash
     ./standalone.sh
     ```

3. **Desplegar la Aplicación**:
    - Una vez que el servidor se haya iniciado correctamente, abre una terminal y navega a la raíz del proyecto.
    - Ejecuta el siguiente comando para limpiar y desplegar la aplicación en WildFly:
      ```bash
      ./mvnw clean wildfly:deploy
      ```

## Uso

1. Una vez desplegado, iremos a http://localhost:8080/employees-management donde estara
la aplicacion web.
