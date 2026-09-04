package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Personaje {

    protected Texture spriteSheet;

    protected TextureRegion idle;

    protected TextureRegion caminarIzquierda1;
    protected TextureRegion caminarIzquierda2;

    protected TextureRegion caminarDerecha1;
    protected TextureRegion caminarDerecha2;

    protected TextureRegion salto1;
    protected TextureRegion salto2;
    protected TextureRegion salto3;
    protected TextureRegion salto4;

    protected TextureRegion frameActual;

    protected float x;
    protected float y;

    protected float ancho;
    protected float alto;

    protected float velocidad;
    protected float velocidadY;

    protected float gravedad;
    protected float fuerzaSalto;

    protected boolean enElSuelo;

    protected float stateTime;

    protected Rectangle hitbox;

    protected Mapa mapa;

    protected int teclaIzquierda;
    protected int teclaDerecha;
    protected int teclaSalto;

    protected Audio audio;

    protected int numeroJugador;

    protected final float ANCHO_PANTALLA = 960;
    protected final float ALTO_PANTALLA = 640;

    public Personaje(
        Mapa mapa,
        String rutaSprite,
        float xInicial,
        float yInicial,
        float velocidad,
        float fuerzaSalto,
        int teclaIzquierda,
        int teclaDerecha,
        int teclaSalto,
        Audio audio,
        int numeroJugador
    ) {

        this.mapa = mapa;

        this.audio = audio;

        this.numeroJugador =
            numeroJugador;

        spriteSheet =
            new Texture(
                rutaSprite
            );

        ancho =
            mapa.getAnchoTile();

        alto =
            mapa.getAltoTile() * 2;

        x = xInicial;
        y = yInicial;

        this.velocidad =
            velocidad;

        velocidadY = 0;

        gravedad = -900f;

        this.fuerzaSalto =
            fuerzaSalto;

        this.teclaIzquierda =
            teclaIzquierda;

        this.teclaDerecha =
            teclaDerecha;

        this.teclaSalto =
            teclaSalto;

        enElSuelo = false;

        stateTime = 0;

        hitbox =
            new Rectangle(
                x,
                y,
                ancho,
                alto
            );
    }

    protected abstract void crearSprites();

    public void actualizar(
        Entrada entrada,
        Personaje otroPersonaje
    ) {

        float delta =
            Gdx.graphics.getDeltaTime();

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

        frameActual =
            obtenerFrame(
                entrada
            );
    }

    private void moverHorizontal(
        Entrada entrada,
        float delta,
        Personaje otroPersonaje
    ) {

        float movimientoX = 0;

        if (
            entrada.teclaPresionada(
                teclaIzquierda
            )
        ) {

            movimientoX =
                -velocidad * delta;
        }

        if (
            entrada.teclaPresionada(
                teclaDerecha
            )
        ) {

            movimientoX =
                velocidad * delta;
        }

        if (movimientoX == 0) {
            return;
        }

        float xAnterior = x;

        x += movimientoX;

        if (x < 0) {
            x = 0;
        }

        if (
            x + ancho
            > ANCHO_PANTALLA
        ) {

            x =
                ANCHO_PANTALLA
                - ancho;
        }

        actualizarHitbox();

        for (
            Rectangle colision :
            mapa.getColisiones()
        ) {

            if (
                hitbox.overlaps(
                    colision
                )
            ) {

                x = xAnterior;

                actualizarHitbox();

                break;
            }
        }

        if (
            mapa.colisionaConPuerta(
                hitbox
            )
        ) {

            x = xAnterior;

            actualizarHitbox();
        }

        if (
            otroPersonaje != null
            &&
            hitbox.overlaps(
                otroPersonaje.getHitbox()
            )
        ) {

            x = xAnterior;

            actualizarHitbox();
        }

        if (enElSuelo) {

            stateTime += delta;
        }
    }

    private void aplicarGravedad(
        float delta,
        Personaje otroPersonaje
    ) {

        velocidadY +=
            gravedad * delta;

        float movimientoY =
            velocidadY * delta;

        float yAnterior =
            y;

        y += movimientoY;

        actualizarHitbox();

        enElSuelo = false;

        for (
            Rectangle colision :
            mapa.getColisiones()
        ) {

            boolean coincideHorizontalmente =
                hitbox.x
                + hitbox.width
                > colision.x
                &&
                hitbox.x
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
                y;

            float cabezaAnterior =
                yAnterior
                + alto;

            float cabezaActual =
                y
                + alto;

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

                y =
                    parteSuperiorBloque;

                velocidadY = 0;

                enElSuelo = true;

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

                y =
                    parteInferiorBloque
                    - alto;

                velocidadY = 0;

                actualizarHitbox();

                break;
            }
        }

        comprobarAscensor(
            mapa.getHitboxAscensorIzquierdo(),
            movimientoY,
            yAnterior
        );

        comprobarAscensor(
            mapa.getHitboxAscensorDerecho(),
            movimientoY,
            yAnterior
        );

        if (
            otroPersonaje != null
            &&
            hitbox.overlaps(
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

                y =
                    otroPersonaje.getY()
                    + otroPersonaje.getAlto();

                velocidadY = 0;

                enElSuelo = true;
            }

            else if (
                movimientoY > 0
                &&
                yAnterior + alto
                <= otroPersonaje.getY()
            ) {

                y =
                    otroPersonaje.getY()
                    - alto;

                velocidadY = 0;
            }

            actualizarHitbox();
        }

        if (y <= 0) {

            y = 0;

            velocidadY = 0;

            enElSuelo = true;

            actualizarHitbox();
        }

        if (
            y + alto
            > ALTO_PANTALLA
        ) {

            y =
                ALTO_PANTALLA
                - alto;

            velocidadY = 0;

            actualizarHitbox();
        }

        if (!enElSuelo) {

            stateTime += delta;
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
            hitbox.x
            + hitbox.width
            > ascensor.x
            &&
            hitbox.x
            < ascensor.x
            + ascensor.width;

        if (!horizontal) {
            return;
        }

        float parteSuperiorAscensor =
            ascensor.y
            + ascensor.height;

        float piesAnteriores =
            yAnterior;

        float piesActuales =
            y;

        if (
            movimientoY <= 0
            &&
            piesAnteriores
            >= parteSuperiorAscensor - 5f
            &&
            piesActuales
            <= parteSuperiorAscensor
        ) {

            y =
                parteSuperiorAscensor;

            velocidadY = 0;

            enElSuelo = true;

            actualizarHitbox();
        }
    }

    private void saltar(
        Entrada entrada
    ) {

        if (
            entrada.teclaJustoPresionada(
                teclaSalto
            )
            &&
            enElSuelo
        ) {

            velocidadY =
                fuerzaSalto;

            enElSuelo = false;

            stateTime = 0;

            audio.detenerPasos(
                numeroJugador
            );

            audio.reproducirSalto();
        }
    }

    private void actualizarSonidoPasos(
        Entrada entrada
    ) {

        boolean moviendose =
            entrada.teclaPresionada(
                teclaIzquierda
            )
            ||
            entrada.teclaPresionada(
                teclaDerecha
            );

        if (
            moviendose
            &&
            enElSuelo
        ) {

            audio.iniciarPasos(
                numeroJugador
            );

        } else {

            audio.detenerPasos(
                numeroJugador
            );
        }
    }

    private void actualizarHitbox() {

        hitbox.set(
            x,
            y,
            ancho,
            alto
        );
    }

    protected TextureRegion obtenerFrame(
        Entrada entrada
    ) {

        if (!enElSuelo) {

            if (
                stateTime < 0.15f
            ) {
                return salto1;
            }

            if (
                stateTime < 0.30f
            ) {
                return salto2;
            }

            if (
                stateTime < 0.45f
            ) {
                return salto3;
            }

            return salto4;
        }

        if (
            entrada.teclaPresionada(
                teclaIzquierda
            )
        ) {

            if (
                (int) (
                    stateTime * 8
                ) % 2 == 0
            ) {

                return caminarIzquierda1;
            }

            return caminarIzquierda2;
        }

        if (
            entrada.teclaPresionada(
                teclaDerecha
            )
        ) {

            if (
                (int) (
                    stateTime * 8
                ) % 2 == 0
            ) {

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

        batch.setProjectionMatrix(
            camera.combined
        );

        batch.enableBlending();

        batch.begin();

        float offsetY = 0;

        if (
            this instanceof Gian
        ) {

            offsetY = -3f;
        }

        batch.draw(
            frameActual,
            x,
            y + offsetY,
            ancho,
            alto
        );

        batch.end();
    }

    public void moverConAscensor(
        float movimientoY
    ) {

        y += movimientoY;

        hitbox.setPosition(
            x,
            y
        );
    }

    public Rectangle getHitbox() {

        return hitbox;
    }

    public float getX() {

        return x;
    }

    public float getY() {

        return y;
    }

    public float getAncho() {

        return ancho;
    }

    public float getAlto() {

        return alto;
    }

    public void dispose() {

        audio.detenerPasos(
            numeroJugador
        );

        spriteSheet.dispose();
    }
}