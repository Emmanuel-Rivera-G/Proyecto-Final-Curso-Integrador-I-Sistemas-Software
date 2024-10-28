# Contribuyendo al Proyecto

Gracias por tu interés en contribuir a este proyecto en Java. Este documento describe las pautas para contribuir al desarrollo del proyecto, incluyendo reglas de estilo, instrucciones de configuración y buenas prácticas. ¡Cualquier contribución, grande o pequeña, es bienvenida!

## Tabla de Contenidos

1. [Cómo empezar](#cómo-empezar)
2. [Reportar problemas](#reportar-problemas)
3. [Solicitudes de extracción (Pull Requests)](#solicitudes-de-extracción-pull-requests)
4. [Estándares de Codificación](#estándares-de-codificación)
5. [Pruebas](#pruebas)
6. [Documentación](#documentación)

## Cómo empezar

Para empezar a contribuir, sigue estos pasos:

1. Haz un fork del repositorio en GitHub.
2. Clona tu fork en tu máquina local.

   ```bash
   git clone https://github.com/tu-usuario/Proyecto-Final-Curso-Integrador-I-Sistemas-Software.git
   ```

3. Crea una rama nueva para tu contribución.

    ```bash
   git checkout -b nombre-de-la-rama
   ```

4. Realiza los cambios en tu rama, asegurándote de seguir las Pautas de Codificación.
5. Realiza commits descriptivos de tus cambios.
6. Realiza pruebas para asegurar que tus cambios no rompan nada.
7. Abre un pull request en el repositorio original y describe los cambios realizados.

## Reportar problemas

Si encuentras un error o tienes una sugerencia de mejora, sigue estos pasos:

1. Asegúrate de que el problema o sugerencia no haya sido reportado anteriormente.
2. Si el problema es nuevo, abre un issue en el repositorio.
3. Describe el problema con el mayor detalle posible, proporcionando el contexto necesario para entenderlo y reproducirlo; si es posible, incluye el código fuente e imagenes de muestra.

### Formato de Issues

Para agilizar la gestión de los issues, proporciona la siguiente información:

- **Descripción del problema:** Explica el error o la sugerencia.
- **Pasos para reproducir:** Describe los pasos para replicar el problema.
- **Sistema y versión:** Indica el sistema operativo y la versión de Java utilizada.
- **Logs y capturas de pantalla:** Incluye cualquier log o captura que pueda ser útil para identificar el problema.

## Solicitudes de extracción (Pull Requests)

Para realizar una solicitud de extracción (Pull Request), sigue estas pautas:

1. Asegúrate de que tu código sigue los Estándares de Codificación y que no introduce errores en el proyecto.
2. Si es un cambio significativo, abre un issue primero para discutir la propuesta.
3. Incluye una descripción clara de los cambios realizados.
4. Agrega pruebas que cubran la nueva funcionalidad o los cambios realizados.
5. Asocia tu Pull Request a un issue existente, si aplica.
6. Asegúrate de que tu código pase todas las pruebas antes de enviar el Pull Request.

## Estándares de Codificación

Por favor, sigue estos estándares de estilo:

- **Formato de Código:** Utiliza spaces o tabs según como en el resto del proyecto.
- **JavaDoc:** Añade JavaDoc en todos los métodos y clases públicas, además de las clases o métodos privados más utilizados.
- **Nombres de Clases y Métodos:** Usa nombres descriptivos y sigue la convención de CamelCase para clases y camelCase para métodos y variables; la estructura de los nombres de clase tiene que ser primero su el _general_ y luego el _específico_ (ejemplo: ControllerModelo).
- **Estructura de Paquetes:** Organiza el código en paquetes según la funcionalidad y asegúrate de que cada clase esté en el paquete correspondiente (No modificar la estructura de paquetes del proyecto).
- **Limpieza de Código:** No dejes código comentado o en desuso, asegurate de que no haya errores de estilo y elimina comentarios de plantillas.
- **Control de Errores:** Maneja todas las excepciones correctamente y usa logs (logback) para reportar errores, en lugar de System.out.println.

    ```java
    /**
     * Realiza una operación específica en el proyecto.
    *
    * @param parametro Un parámetro necesario para el método.
    * @return Resultado de la operación.
    * @throws MiExcepcion si ocurre un error durante la operación.
    */
    public Resultado miMetodo(Tipo parametro) throws MiExcepcion {
        // Implementación
    }
    ```

## Pruebas

Este proyecto utiliza JUnit para las pruebas unitarias. Asegúrate de que tus cambios pasen todas las pruebas antes de enviar un Pull Request. Si agregas una nueva funcionalidad, incluye pruebas unitarias para cubrirla.

Para ejecutar las pruebas, utiliza:

```bash
ant test
```

o

En netbeans presiona _click derecho_ y ejecutar Test (Alt + F6).

### Especificaciones  de pruebas

- **Cobertura:** Asegúrate de que la cobertura de las pruebas sea adecuada, especialmente en los métodos públicos.
- **Nombres Descriptivos:** Nombra los métodos de prueba de forma descriptiva para reflejar la funcionalidad que están validando.
- **Organización:** Organiza las clases de prueba siguiendo la estructura de paquetes del proyecto.

## Documentación

Documenta cada cambio de funcionalidad y asegúrate de actualizar los archivos README o cualquier otro archivo relevante si la funcionalidad cambia. Toda nueva clase pública debe tener su JavaDoc.

Para generar el JavaDoc completo del proyecto (en NetBeans con Ant):

1. Navega a la carpeta del proyecto.
2. Ejecuta:

    ```bash
    ant javadoc
    ```

o

Presiona _click derecho_ en la carpeta del proyecto y ejecutar JavaDoc.

Los archivos generados estarán en la carpeta _**dist/javadoc**_.

---

### **Gracias por contribuir a este proyecto. ¡Esperamos tu aportación!**
