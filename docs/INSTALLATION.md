# Instalación del Proyecto

Este documento describe cómo instalar y ejecutar el proyecto Java utilizando **Apache Ant** y **NetBeans**.

## Requisitos Previos

Asegúrate de tener instalados los siguientes elementos en tu sistema antes de continuar con la instalación del proyecto:

- **Java Development Kit (JDK)** versión 20
  - [Descargar JDK 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
- **NetBeans IDE** versión 12 o superior (recomendado  versión 20)
  - [Descargar NetBeans](https://netbeans.apache.org/download/index.html)

## Clonar el Repositorio

Primero, clona el repositorio en tu máquina local:

```bash
git clone https://github.com/Emmanuel-Rivera-G/Proyecto-Final-Curso-Integrador-I-Sistemas-Software.git
```

Luego, navega a la carpeta del proyecto:

```bash
cd Proyecto-Final-Curso-Integrador-I-Sistemas-Software
```

## Configuración del Proyecto en NetBeans

1. Abre **NetBeans IDE**.
2. Selecciona `File` > `Open Project....`
3. **Navega** a la carpeta donde clonaste el repositorio y selecciona el proyecto.
4. **Haz clic** en Open Project para cargar el proyecto en NetBeans.
**Nota:** Asegurate de tener las dependencias en la carpeta lib.

## Compilar el Proyecto

Para compilar el proyecto en NetBeans:

1. En el panel **Projects**, haz clic derecho sobre el proyecto.
2. Selecciona **Build** o **Clean and Build** para compilar todo el proyecto.

## Ejecutar el Proyecto

Para ejecutar el proyecto en NetBeans:

1. **Haz clic** derecho en el proyecto desde el panel Projects.
2. Selecciona Run o utiliza el atajo de teclado F6.
**Nota:** La clase principal es `ViewInicioSesion.java` ubicada en `view/`.

## Generar Javadoc

Para generar la documentación de Javadoc:

1. Abre **NetBeans**.
2. **Haz clic** derecho en el proyecto y selecciona Generate Javadoc.

La documentación generada estará disponible en la carpeta dist/javadoc.

## Ejecutar Pruebas

El proyecto contiene pruebas unitarias (JUnit), puedes ejecutarlas en NetBeans:

**Haz clic** _derecho_ en el proyecto y selecciona Test.

## Solución de Problemas

- **Error de configuración en NetBeans:** Asegúrate de que NetBeans esté configurado para utilizar la versión correcta del JDK (verificar archivos de configuración de `nbproject`).
- **Dependencias faltantes:** Verifica que todos los archivos de dependencias estén en la carpeta lib del proyecto o que estén correctamente referenciados en `build.xml` y `lib/nblibraries.properties`.

---

**¡Ya estás listo para empezar a trabajar en el proyecto! Si tienes alguna pregunta o problema, revisa la sección de CONTRIBUTING.md para ver las pautas de colaboración.**
