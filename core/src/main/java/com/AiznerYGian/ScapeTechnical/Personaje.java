package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Personaje {

    private Texture spriteSheet;

    private TextureRegion idle;

    private TextureRegion caminarIzquierda1;
    private TextureRegion caminarIzquierda2;

    private TextureRegion caminarDerecha1;
    private TextureRegion caminarDerecha2;

    private TextureRegion salto1;
    private TextureRegion salto2;
    private TextureRegion salto3;
    private TextureRegion salto4;

    private TextureRegion frameActual;

    private float x;
    private float y;

    private float velocidad = 200f;

    private float velocidadY = 0;

    private float gravedad = -900f;

    private float fuerzaSalto = 450f;

    private boolean enElSuelo = true;

    private float stateTime = 0;

    public Personaje() {

        spriteSheet = new Texture("personajes/az.png");

        crearSprites();

        x = 100;

        y = 100;

        frameActual = idle;
    }

    private void crearSprites() {

        idle = new TextureRegion(
            spriteSheet,
            20,
            740,
            220,
            320
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
            42,
            220,
            298
        );

        salto2 = new TextureRegion(
            spriteSheet,
            280,
            42,
            220,
            298
        );

        salto3 = new TextureRegion(
            spriteSheet,
            540,
            42,
            220,
            298
        );

        salto4 = new TextureRegion(
            spriteSheet,
            800,
            42,
            220,
            298
        );
    }

    public void actualizar(Entrada entrada) {

        mover(entrada);

        aplicarGravedad();

        frameActual = obtenerFrame(entrada);
    }

    private void mover(Entrada entrada) {

        float delta = Gdx.graphics.getDeltaTime();

        float velocidadActual = velocidad * delta;

        boolean caminando = false;

        if (entrada.izquierda()) {

            x -= velocidadActual;

            caminando = true;
        }

        if (entrada.derecha()) {

            x += velocidadActual;

            caminando = true;
        }

        if (x < 0) {

            x = 0;
        }

        if (x > 880) {

            x = 880;
        }

        if (caminando && enElSuelo) {

            stateTime += delta;
        }

        if (entrada.salto() && enElSuelo) {

            velocidadY = fuerzaSalto;

            enElSuelo = false;

            stateTime = 0;
        }
    }

    private void aplicarGravedad() {

        if (!enElSuelo) {

            float delta = Gdx.graphics.getDeltaTime();

            stateTime += delta;

            velocidadY += gravedad * delta;

            y += velocidadY * delta;

            if (y <= 100) {

                y = 100;

                velocidadY = 0;

                enElSuelo = true;

                stateTime = 0;
            }
        }
    }

    private TextureRegion obtenerFrame(Entrada entrada) {

        if (!enElSuelo) {

            if (stateTime < 0.15f) {

                return salto1;
            }

            if (stateTime < 0.30f) {

                return salto2;
            }

            if (stateTime < 0.45f) {

                return salto3;
            }

            return salto4;
        }

        if (entrada.izquierda()) {

            if ((int) (stateTime * 8) % 2 == 0) {

                return caminarIzquierda1;
            }

            return caminarIzquierda2;
        }

        if (entrada.derecha()) {

            if ((int) (stateTime * 8) % 2 == 0) {

                return caminarDerecha1;
            }

            return caminarDerecha2;
        }

        return idle;
    }

    public void dibujar(
        SpriteBatch batch,
        OrthographicCamera camera
    ) {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(
            frameActual,
            x,
            y,
            80,
            120
        );

        batch.end();
    }

    public void dispose() {

        spriteSheet.dispose();
    }
}