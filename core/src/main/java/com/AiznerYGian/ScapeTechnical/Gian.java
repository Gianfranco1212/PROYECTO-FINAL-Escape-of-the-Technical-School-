package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Gian extends Personaje {

    public Gian(
        Mapa mapa,
        Audio audio
    ) {

        super(
            mapa,
            "personajes/Gian.png",
            510,
            65,
            120f,
            320f,
            Input.Keys.LEFT,
            Input.Keys.RIGHT,
            Input.Keys.UP,
            audio,
            2
        );

        spriteSheet.setFilter(
            Texture.TextureFilter.Nearest,
            Texture.TextureFilter.Nearest
        );

        spriteSheet.setWrap(
            Texture.TextureWrap.ClampToEdge,
            Texture.TextureWrap.ClampToEdge
        );

        crearSprites();

        frameActual = idle;
    }

    @Override
    protected void crearSprites() {

        idle = new TextureRegion(
            spriteSheet,
            40,
            40,
            250,
            380
        );

        caminarIzquierda1 =
            new TextureRegion(
                spriteSheet,
                40,
                430,
                250,
                380
            );

        caminarIzquierda2 =
            new TextureRegion(
                spriteSheet,
                350,
                430,
                250,
                380
            );

        caminarDerecha1 =
            new TextureRegion(
                spriteSheet,
                650,
                430,
                250,
                380
            );

        caminarDerecha2 =
            new TextureRegion(
                spriteSheet,
                930,
                430,
                250,
                380
            );

        salto1 =
            new TextureRegion(
                spriteSheet,
                40,
                810,
                250,
                400
            );

        salto2 =
            new TextureRegion(
                spriteSheet,
                350,
                810,
                250,
                400
            );

        salto3 =
            new TextureRegion(
                spriteSheet,
                650,
                810,
                250,
                400
            );

        salto4 =
            new TextureRegion(
                spriteSheet,
                930,
                810,
                250,
                400
            );
    }
}