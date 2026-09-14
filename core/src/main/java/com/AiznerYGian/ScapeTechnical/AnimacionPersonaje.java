package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimacionPersonaje {

    private Personaje personaje;

    public AnimacionPersonaje(
        Personaje personaje
    ) {

        this.personaje =
            personaje;
    }

    public TextureRegion obtenerFrame(
        Entrada entrada
    ) {

        if (
            !personaje.enElSuelo
        ) {

            if (
                personaje.stateTime
                < 0.15f
            ) {

                return personaje.salto1;
            }

            if (
                personaje.stateTime
                < 0.30f
            ) {

                return personaje.salto2;
            }

            if (
                personaje.stateTime
                < 0.45f
            ) {

                return personaje.salto3;
            }

            return personaje.salto4;
        }

        if (
            entrada.teclaPresionada(
                personaje.teclaIzquierda
            )
        ) {

            if (
                (int)
                (
                    personaje.stateTime
                    * 8
                )
                % 2
                == 0
            ) {

                return
                    personaje.caminarIzquierda1;
            }

            return
                personaje.caminarIzquierda2;
        }

        if (
            entrada.teclaPresionada(
                personaje.teclaDerecha
            )
        ) {

            if (
                (int)
                (
                    personaje.stateTime
                    * 8
                )
                % 2
                == 0
            ) {

                return
                    personaje.caminarDerecha1;
            }

            return
                personaje.caminarDerecha2;
        }

        return personaje.idle;
    }
}