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
-Incorporación del mapa del primer nivel del juego, correspondiente a la Cantina de la escuela, creado mediante Tiled e integrado al proyecto con TmxMapLoader.

-Incorporación del personaje principal al mapa mediante un sprite sheet.

-Implementación de las animaciones del personaje para los estados quieto, caminando y saltando.

-Implementación del movimiento horizontal del personaje mediante las teclas A y D.

-Implementación del salto del personaje mediante la tecla W.

-Incorporación de gravedad básica y detección provisoria del suelo para controlar el salto y la caída del personaje.

-Incorporación de un menú de inicio simple con el título del juego y acceso mediante la tecla ENTER.

-Incorporación de la clase Entrada para centralizar el manejo de las entradas del jugador.

-Incorporación de la clase Mapa para administrar la carga y renderizado del mapa de Tiled.
-Incorporación de la clase Personaje para administrar el movimiento, salto, gravedad y animaciones del personaje.
-Incorporación de la clase MenuInicio para administrar y mostrar el menú inicial.
