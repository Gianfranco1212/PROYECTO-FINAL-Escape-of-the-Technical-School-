package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.math.Rectangle;

public class MovimientoPersonaje {

    private Personaje personaje;

    public MovimientoPersonaje(
        Personaje personaje
    ) {

        this.personaje =
            personaje;
    }

    public void actualizar(
        Entrada entrada,
        Personaje otroPersonaje,
        float delta
    ) {

        moverHorizontal(
            entrada,
            delta,
            otroPersonaje
        );

        aplicarGravedad(
            delta,
            otroPersonaje
        );

        saltar(
            entrada
        );

        actualizarSonidoPasos(
            entrada
        );
    }

    private void moverHorizontal(
        Entrada entrada,
        float delta,
        Personaje otroPersonaje
    ) {

        float movimientoX =
            0;

        if (
            entrada.teclaPresionada(
                personaje.teclaIzquierda
            )
        ) {

            movimientoX =
                -personaje.velocidad
                * delta;
        }

        if (
            entrada.teclaPresionada(
                personaje.teclaDerecha
            )
        ) {

            movimientoX =
                personaje.velocidad
                * delta;
        }

        if (
            movimientoX == 0
        ) {

            return;
        }

        float xAnterior =
            personaje.x;

        personaje.x +=
            movimientoX;

        if (
            personaje.x < 0
        ) {

            personaje.x =
                0;
        }

        if (
            personaje.x
            + personaje.ancho
            > personaje.ANCHO_PANTALLA
        ) {

            personaje.x =
                personaje.ANCHO_PANTALLA
                - personaje.ancho;
        }

        actualizarHitbox();

        for (
            Rectangle colision :
            personaje.mapa.getColisiones()
        ) {

            if (
                personaje.hitbox.overlaps(
                    colision
                )
            ) {

                personaje.x =
                    xAnterior;

                actualizarHitbox();

                break;
            }
        }

        if (
            personaje.mapa.colisionaConPuerta(
                personaje.hitbox
            )
        ) {

            personaje.x =
                xAnterior;

            actualizarHitbox();
        }

        if (
            otroPersonaje != null
            &&
            personaje.hitbox.overlaps(
                otroPersonaje.getHitbox()
            )
        ) {

            personaje.x =
                xAnterior;

            actualizarHitbox();
        }

        if (
            personaje.enElSuelo
        ) {

            personaje.stateTime +=
                delta;
        }
    }

    private void aplicarGravedad(
        float delta,
        Personaje otroPersonaje
    ) {

        personaje.velocidadY +=
            personaje.gravedad
            * delta;

        float movimientoY =
            personaje.velocidadY
            * delta;

        float yAnterior =
            personaje.y;

        personaje.y +=
            movimientoY;

        actualizarHitbox();

        personaje.enElSuelo =
            false;

        for (
            Rectangle colision :
            personaje.mapa.getColisiones()
        ) {

            boolean coincideHorizontalmente =
                personaje.hitbox.x
                + personaje.hitbox.width
                > colision.x
                &&
                personaje.hitbox.x
                < colision.x
                + colision.width;

            if (
                !coincideHorizontalmente
            ) {

                continue;
            }

            float piesAnteriores =
                yAnterior;

            float piesActuales =
                personaje.y;

            float cabezaAnterior =
                yAnterior
                + personaje.alto;

            float cabezaActual =
                personaje.y
                + personaje.alto;

            float parteSuperiorBloque =
                colision.y
                + colision.height;

            float parteInferiorBloque =
                colision.y;

            if (
                movimientoY < 0
                &&
                piesAnteriores
                >= parteSuperiorBloque
                &&
                piesActuales
                <= parteSuperiorBloque
            ) {

                personaje.y =
                    parteSuperiorBloque;

                personaje.velocidadY =
                    0;

                personaje.enElSuelo =
                    true;

                actualizarHitbox();

                break;
            }

            if (
                movimientoY > 0
                &&
                cabezaAnterior
                <= parteInferiorBloque
                &&
                cabezaActual
                >= parteInferiorBloque
            ) {

                personaje.y =
                    parteInferiorBloque
                    - personaje.alto;

                personaje.velocidadY =
                    0;

                actualizarHitbox();

                break;
            }
        }

        comprobarAscensor(
            personaje.mapa
                .getHitboxAscensorIzquierdo(),
            movimientoY,
            yAnterior
        );

        comprobarAscensor(
            personaje.mapa
                .getHitboxAscensorDerecho(),
            movimientoY,
            yAnterior
        );

        if (
            otroPersonaje != null
            &&
            personaje.hitbox.overlaps(
                otroPersonaje.getHitbox()
            )
        ) {

            if (
                movimientoY < 0
                &&
                yAnterior
                >= otroPersonaje.getY()
                + otroPersonaje.getAlto()
            ) {

                personaje.y =
                    otroPersonaje.getY()
                    + otroPersonaje.getAlto();

                personaje.velocidadY =
                    0;

                personaje.enElSuelo =
                    true;
            }

            else if (
                movimientoY > 0
                &&
                yAnterior
                + personaje.alto
                <= otroPersonaje.getY()
            ) {

                personaje.y =
                    otroPersonaje.getY()
                    - personaje.alto;

                personaje.velocidadY =
                    0;
            }

            actualizarHitbox();
        }

        if (
            personaje.y <= 0
        ) {

            personaje.y =
                0;

            personaje.velocidadY =
                0;

            personaje.enElSuelo =
                true;

            actualizarHitbox();
        }

        if (
            personaje.y
            + personaje.alto
            > personaje.ALTO_PANTALLA
        ) {

            personaje.y =
                personaje.ALTO_PANTALLA
                - personaje.alto;

            personaje.velocidadY =
                0;

            actualizarHitbox();
        }

        if (
            !personaje.enElSuelo
        ) {

            personaje.stateTime +=
                delta;
        }
    }

    private void comprobarAscensor(
        Rectangle ascensor,
        float movimientoY,
        float yAnterior
    ) {

        if (
            ascensor == null
        ) {

            return;
        }

        boolean horizontal =
            personaje.hitbox.x
            + personaje.hitbox.width
            > ascensor.x
            &&
            personaje.hitbox.x
            < ascensor.x
            + ascensor.width;

        if (
            !horizontal
        ) {

            return;
        }

        float parteSuperiorAscensor =
            ascensor.y
            + ascensor.height;

        float piesAnteriores =
            yAnterior;

        float piesActuales =
            personaje.y;

        if (
            movimientoY <= 0
            &&
            piesAnteriores
            >= parteSuperiorAscensor
                - 5f
            &&
            piesActuales
            <= parteSuperiorAscensor
        ) {

            personaje.y =
                parteSuperiorAscensor;

            personaje.velocidadY =
                0;

            personaje.enElSuelo =
                true;

            actualizarHitbox();
        }
    }

    private void saltar(
        Entrada entrada
    ) {

        if (
            entrada.teclaJustoPresionada(
                personaje.teclaSalto
            )
            &&
            personaje.enElSuelo
        ) {

            personaje.velocidadY =
                personaje.fuerzaSalto;

            personaje.enElSuelo =
                false;

            personaje.stateTime =
                0;

            personaje.audio.detenerPasos(
                personaje.numeroJugador
            );

            personaje.audio.reproducirSalto();
        }
    }

    private void actualizarSonidoPasos(
        Entrada entrada
    ) {

        boolean moviendose =
            entrada.teclaPresionada(
                personaje.teclaIzquierda
            )
            ||
            entrada.teclaPresionada(
                personaje.teclaDerecha
            );

        if (
            moviendose
            &&
            personaje.enElSuelo
        ) {

            personaje.audio.iniciarPasos(
                personaje.numeroJugador
            );

        } else {

            personaje.audio.detenerPasos(
                personaje.numeroJugador
            );
        }
    }

    private void actualizarHitbox() {

        personaje.hitbox.set(
            personaje.x,
            personaje.y,
            personaje.ancho,
            personaje.alto
        );
    }

    public void moverConAscensor(
        float movimientoY
    ) {

        personaje.y +=
            movimientoY;

        personaje.hitbox.setPosition(
            personaje.x,
            personaje.y
        );
    }
}