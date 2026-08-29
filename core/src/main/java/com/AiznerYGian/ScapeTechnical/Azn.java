package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Azn extends Personaje {

    public Azn(Mapa mapa) {

        super(
            mapa,
            "personajes/az.png",

            335,
            65,

            120f,
            300f,

            Input.Keys.A,
            Input.Keys.D,
            Input.Keys.W
        );

        crearSprites();

        frameActual = idle;
    }

    @Override
    protected void crearSprites() {

        idle = new TextureRegion(
            spriteSheet,
            20,
            42,
            220,
            298
        );

        caminarIzquierda1 = new TextureRegion(
            spriteSheet,
            20,
            381,
            220,
            319
        );

        caminarIzquierda2 = new TextureRegion(
            spriteSheet,
            280,
            381,
            220,
            319
        );

        caminarDerecha1 = new TextureRegion(
            spriteSheet,
            540,
            381,
            220,
            319
        );

        caminarDerecha2 = new TextureRegion(
            spriteSheet,
            800,
            381,
            220,
            319
        );

        salto1 = new TextureRegion(
            spriteSheet,
            20,
            740,
            220,
            320
        );

        salto2 = new TextureRegion(
            spriteSheet,
            280,
            740,
            220,
            320
        );

        salto3 = new TextureRegion(
            spriteSheet,
            540,
            740,
            220,
            320
        );

        salto4 = new TextureRegion(
            spriteSheet,
            800,
            740,
            220,
            320
        );
    }
}