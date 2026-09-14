# Escape of the Technical School 🚀

## Descripción
"Escape of the Technical School" es un videojuego cooperativo de puzles y acertijos en 2D. Dos personajes deben recorrer distintos sectores de la Escuela Técnica N.º 35 para resolver desafíos y lograr escapar.

## Integrantes
- Gianfranco Castaldini
- Tomás Agustín Aizner

## Tecnologías Utilizadas
- **Lenguaje:** Java 21.0.6
- **Framework:** LibGDX 1.14.2
- **Persistencia:** SQL (MySQL/MariaDB)
- **IDE utilizado:** Eclipse IDE
- **Plataforma objetivo:** Desktop (LWJGL3)

## Cómo Ejecutar el Proyecto
**Requisitos previos**
- JDK 21 instalado.
- Eclipse IDE.
- Git

**Clonacion del repositorio**
1. Ingresar al repositorio en GitHub.
2. Hacer clic en el botón Code.
3. Copiar la URL del repositorio.
4. Abrir Git Bash (Terminal de git).
5. Ejecutar el siguiente comando, reemplazando la URL por la del repositorio: git clone <URL_DEL_REPOSITORIO>

**Importar el proyecto**
1. Abrir Eclipse IDE.
2. Seleccionar File y luego Import.
3. Elegir Gradle y Existing Gradle Project.
4. Presionar Next.
5. Seleccionar la carpeta raíz del proyecto.
6. Presionar Finish y esperar a que Gradle descargue todas las dependencias.
   
**Ejecutar el proyecto**
1. En el Package Explorer, expandir el proyecto lwjgl3.
2. Abrir el paquete donde se encuentra la clase Lwjgl3Launcher.
3. Hacer clic derecho sobre Lwjgl3Launcher.java.
4. Seleccionar Run As y Java Application.

## Características Implementadas
- Primer nivel jugable ambientado en la Cantina de la Escuela Técnica N.º 35.
- Modo cooperativo local para dos jugadores.
- Dos personajes jugables: AZN y GIAN.
- Movimiento horizontal, salto y gravedad.
- Animaciones de los personajes al estar quietos, caminar y saltar.
- Sistema de colisiones con el escenario.
- Colisiones entre los personajes.
- Límites para evitar que los personajes salgan del escenario.
- Sistema de ascensores y plataformas móviles.
- Botones que permiten activar diferentes mecanismos.
- Puertas azul y roja controladas mediante botones.
- Puerta final que se abre al conseguir la llave.
- Sistema de monedas coleccionables.
- Llave necesaria para completar el nivel.
- HUD con tiempo, monedas y estado de la llave.
- Menú principal.
- Menú de pausa.
- Menú de ajustes de sonido y música.
- Pantalla de victoria con el tiempo y las monedas obtenidas.
- Música y efectos de sonido.
- Reinicio del nivel.
- Condición de victoria cuando ambos jugadores llegan juntos a la salida.
- Organización del código en diferentes clases para separar personajes, movimiento, animaciones, mapa, colisiones, recolectables y mecanismos.

## Controles

### Jugador 1 - AZN

- **A:** Moverse hacia la izquierda.
- **D:** Moverse hacia la derecha.
- **W:** Saltar.

### Jugador 2 - GIAN

- **Flecha izquierda:** Moverse hacia la izquierda.
- **Flecha derecha:** Moverse hacia la derecha.
- **Flecha arriba:** Saltar.

### General

- **ESC:** Abrir o cerrar el menú de pausa.

## Estado Actual del Proyecto
El proyecto se encuentra actualmente en etapa de prototipo jugable. El primer nivel, correspondiente a la Cantina, ya puede ser recorrido por los dos jugadores y cuenta con las mecánicas principales necesarias para completarlo.
Los jugadores pueden moverse, saltar, interactuar con botones, utilizar ascensores, abrir puertas, recolectar monedas y obtener la llave necesaria para abrir la puerta final. El nivel finaliza cuando ambos personajes llegan juntos a la zona de salida.
Además, el prototipo cuenta con HUD, música, efectos de sonido, menú principal, pausa, ajustes y pantalla de victoria.

## Enlace a la Wiki del Proyecto (Propuesta Detallada):
[Ver la Propuesta Completa del Proyecto aquí](https://github.com/Gianfranco1212/PROYECTO-FINAL-Escape-of-the-Technical-School-/wiki/Propuesta-Formal-de-Proyecto-Final-%E2%80%90-%5BEscape-oh-the-Technical-School%5D)
