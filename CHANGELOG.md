## Changelog ##

Todos los cambios importantes realizados en este proyecto serán documentados en este archivo.

## [0.1.0] - 2026-07-02

### Added (Añadido)
- Incorporacion del archivo README.md

## [0.1.1] - 2026-07-12
### Added (Añadido)
- Creación y configuración inicial del proyecto utilizando LibGDX 1.14.2.
- Configuración del entorno de desarrollo con Java 21.0.6.
- Incorporación de la estructura base del proyecto generada por LibGDX Liftoff.
- Inclusión del archivo .gitignore para excluir archivos temporales y de compilación del repositorio.
- Publicación de la Wiki del repositorio con la Propuesta Formal del Proyecto Final.
- Incorporación de la documentación inicial del proyecto.

## [0.1.2] - 2026-07-13
### Fixed (Corregido)
- Correccion del archivo README.md, agregandole las tecnologías usadas, plataforma objetivo e instrucciones de ejecución paso a paso comprobables.
- Corrección de la Wiki del proyecto, estableciendo la propuesta formal como página principal, eliminando la página predeterminada de GitHub y organizando la documentación para mantenerla actualizada durante el desarrollo del proyecto.

## [0.2.0] - 2026-08-27
### Added (Añadido)
- Incorporación del mapa del primer nivel del juego, correspondiente a la Cantina de la escuela, creado mediante Tiled e integrado al proyecto con TmxMapLoader.

- Incorporación del personaje principal al mapa mediante un sprite sheet.

- Implementación de las animaciones del personaje para los estados quieto, caminando y saltando.

- Implementación del movimiento horizontal del personaje mediante las teclas A y D.

- Implementación del salto del personaje mediante la tecla W.

- Incorporación de gravedad básica y detección provisoria del suelo para controlar el salto y la caída del personaje.

- Incorporación de un menú de inicio simple con el título del juego y acceso mediante la tecla ENTER.

- Incorporación de la clase Entrada para centralizar el manejo de las entradas del jugador.

- Incorporación de la clase Mapa para administrar la carga y renderizado del mapa de Tiled.
  
- Incorporación de la clase Personaje para administrar el movimiento, salto, gravedad y animaciones del personaje.

- Incorporación de la clase MenuInicio para administrar y mostrar el menú inicial.

## [0.3.0] - 2026-08-28

### Added (Añadido)
- Incorporación de colisiones del mapa mediante objetos definidos en Tiled, permitiendo que los personajes se apoyen sobre el suelo y las plataformas.

- Implementación de límites de pantalla para evitar que los personajes puedan salir del área visible del nivel.

- Incorporación del segundo personaje jugable `Gian`.

- Implementación de dos personajes jugables simultáneamente dentro del mismo nivel.

- Incorporación de posiciones iniciales diferentes para AZN y Gian sobre las mesas del nivel.

- Implementación de colisiones entre los dos personajes para evitar que puedan atravesarse entre sí.

## [0.4.0] - 2026-09-14

### Added (Añadido)
- Incorporación del sistema de ascensores y plataformas móviles dentro del primer nivel.
  
- Incorporación de un botón que permite activar el movimiento de los ascensores.
  
- Implementación del movimiento de los personajes junto con los ascensores cuando se encuentran sobre ellos.
  
- Incorporación de puertas azul y roja que pueden abrirse mediante sus respectivos botones.
  
- Incorporación de una puerta final para completar el nivel.
  
- Incorporación de una llave que debe ser recolectada para abrir la puerta final.
  
- Incorporación de monedas coleccionables distribuidas por el nivel.
  
- Incorporación de un HUD para mostrar el tiempo transcurrido, las monedas recolectadas y el estado de la llave.
  
- Incorporación de música y efectos de sonido para el nivel, los saltos y el movimiento de los personajes.
  
- Incorporación de un menú de pausa con opciones para reanudar, reiniciar, acceder a ajustes o volver al menú principal.
  
- Incorporación de un menú de ajustes para modificar el volumen de la música y de los efectos de sonido.
  
- Incorporación de una pantalla de victoria que muestra el tiempo y la cantidad de monedas obtenidas.
  
- Implementación de la condición de victoria cuando ambos jugadores llegan juntos a la zona de salida luego de obtener la llave.
  
- Incorporación del reinicio completo del nivel.

### Changed (Modificado)
- Reorganización de la clase Personaje para separar sus responsabilidades de movimiento y animación.
  
- Incorporación de MovimientoPersonaje para administrar el movimiento, gravedad, salto y colisiones de los personajes.
  
- Incorporación de AnimacionPersonaje para administrar las animaciones de los personajes.
  
- Reorganización de la clase Mapa para evitar concentrar toda la lógica del nivel en una única clase.
  
- Incorporación de ColisionesMapa para administrar las colisiones estáticas definidas en Tiled.
  
- Incorporación de RecolectablesMapa para administrar las monedas y la llave.
  
- Incorporación de MecanismosMapa para centralizar el acceso a los diferentes mecanismos del nivel.
  
- División de la lógica de MecanismosMapa en MecanismoAscensores, MecanismoPuertas y MecanismoPuertaFinal.
  
- Actualización de la clase Entrada para permitir el manejo de las teclas de los jugadores mediante LibGDX.
  
- Mejora de la organización general del código, separando las responsabilidades en clases más pequeñas y específicas.

### Fixed (Corregido)
- Corrección de las colisiones para evitar que los personajes atraviesen los bloques del mapa.
  
- Corrección del comportamiento de gravedad para evitar que los personajes permanezcan flotando.
  
- Corrección de los límites del nivel para evitar que los personajes salgan del área jugable.
  
- Corrección de las colisiones entre los dos personajes.
  
- Corrección del movimiento de los personajes sobre los ascensores para que acompañen el desplazamiento de la plataforma.
  
- Corrección de las colisiones de las puertas para evitar que continúen bloqueando el paso luego de abrirse.
  
- Corrección de la detección de los botones de las puertas para activarlos cuando un personaje se encuentra sobre ellos.
  
- Corrección de la carga de recursos del mapa de Tiled y de las referencias a los archivos utilizados por los tilesets.
  
- Corrección del tamaño y posicionamiento del HUD al modificar el tamaño de la ventana.
  
- Corrección de la animación del personaje para mostrar correctamente los estados quieto, caminando y saltando.



