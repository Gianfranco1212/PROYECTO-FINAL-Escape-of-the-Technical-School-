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

    protected final float ANCHO_PANTALLA =
        960;

    protected final float ALTO_PANTALLA =
        640;

    private MovimientoPersonaje movimiento;
    private AnimacionPersonaje animacion;

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

        this.mapa =
            mapa;

        this.audio =
            audio;

        this.numeroJugador =
            numeroJugador;

        spriteSheet =
            new Texture(
                rutaSprite
            );

        ancho =
            mapa.getAnchoTile();

        alto =
            mapa.getAltoTile()
            * 2;

        x =
            xInicial;

        y =
            yInicial;

        this.velocidad =
            velocidad;

        velocidadY =
            0;

        gravedad =
            -900f;

        this.fuerzaSalto =
            fuerzaSalto;

        this.teclaIzquierda =
            teclaIzquierda;

        this.teclaDerecha =
            teclaDerecha;

        this.teclaSalto =
            teclaSalto;

        enElSuelo =
            false;

        stateTime =
            0;

        hitbox =
            new Rectangle(
                x,
                y,
                ancho,
                alto
            );

        movimiento =
            new MovimientoPersonaje(
                this
            );

        animacion =
            new AnimacionPersonaje(
                this
            );
    }

    protected abstract void crearSprites();

    public void actualizar(
        Entrada entrada,
        Personaje otroPersonaje
    ) {

        float delta =
            Gdx.graphics.getDeltaTime();

        movimiento.actualizar(
            entrada,
            otroPersonaje,
            delta
        );

        frameActual =
            obtenerFrame(
                entrada
            );
    }

    protected TextureRegion obtenerFrame(
        Entrada entrada
    ) {

        return animacion.obtenerFrame(
            entrada
        );
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

        float offsetY =
            0;

        if (
            this instanceof Gian
        ) {

            offsetY =
                -3f;
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

        movimiento.moverConAscensor(
            movimientoY
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