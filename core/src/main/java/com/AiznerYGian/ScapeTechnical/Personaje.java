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
        int teclaSalto
    ) {

        this.mapa = mapa;

        spriteSheet = new Texture(rutaSprite);

        ancho = mapa.getAnchoTile();
        alto = mapa.getAltoTile() * 2;

        x = xInicial;
        y = yInicial;

        this.velocidad = velocidad;

        velocidadY = 0;

        gravedad = -900f;

        this.fuerzaSalto = fuerzaSalto;

        this.teclaIzquierda = teclaIzquierda;
        this.teclaDerecha = teclaDerecha;
        this.teclaSalto = teclaSalto;

        enElSuelo = false;

        stateTime = 0;

        hitbox = new Rectangle(
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

    	    float delta = Gdx.graphics.getDeltaTime();

    	    moverHorizontal(
    	        entrada,
    	        delta,
    	        otroPersonaje
    	    );

    	    aplicarGravedad(
    	        delta,
    	        otroPersonaje
    	    );

    	    saltar(entrada);

    	    frameActual =
    	        obtenerFrame(entrada);
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

    	    // COLISION CON EL MAPA
    	    for (
    	        Rectangle colision :
    	        mapa.getColisiones()
    	    ) {

    	        if (
    	            hitbox.overlaps(colision)
    	        ) {

    	            x = xAnterior;

    	            actualizarHitbox();

    	            break;
    	        }
    	    }

    	    // COLISION CON EL OTRO PERSONAJE
    	    if (
    	        otroPersonaje != null
    	        && hitbox.overlaps(
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

    	    float yAnterior = y;

    	    y += movimientoY;

    	    if (y < 0) {

    	        y = 0;

    	        velocidadY = 0;

    	        enElSuelo = true;
    	    }

    	    if (
    	        y + alto
    	        > ALTO_PANTALLA
    	    ) {

    	        y =
    	            ALTO_PANTALLA
    	            - alto;

    	        velocidadY = 0;
    	    }

    	    actualizarHitbox();

    	    enElSuelo = false;

    	    // COLISION VERTICAL CON EL MAPA
    	    for (
    	        Rectangle colision :
    	        mapa.getColisiones()
    	    ) {

    	        if (
    	            hitbox.overlaps(colision)
    	        ) {

    	            if (movimientoY < 0) {

    	                y =
    	                    colision.y
    	                    + colision.height;

    	                velocidadY = 0;

    	                enElSuelo = true;
    	            }

    	            else if (
    	                movimientoY > 0
    	            ) {

    	                y =
    	                    colision.y
    	                    - alto;

    	                velocidadY = 0;
    	            }

    	            actualizarHitbox();
    	        }
    	    }

    	    // COLISION VERTICAL CON EL OTRO PERSONAJE
    	    if (
    	        otroPersonaje != null
    	        && hitbox.overlaps(
    	            otroPersonaje.getHitbox()
    	        )
    	    ) {

    	        if (movimientoY < 0) {

    	            y =
    	                otroPersonaje.getY()
    	                + otroPersonaje.getAlto();

    	            velocidadY = 0;

    	            enElSuelo = true;
    	        }

    	        else if (
    	            movimientoY > 0
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

    	    if (!enElSuelo) {
    	        stateTime += delta;
    	    }
    	}

    private void saltar(Entrada entrada) {

        if (
            entrada.teclaJustoPresionada(teclaSalto)
            && enElSuelo
        ) {

            velocidadY = fuerzaSalto;

            enElSuelo = false;

            stateTime = 0;
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

        if (entrada.teclaPresionada(teclaIzquierda)) {

            if ((int) (stateTime * 8) % 2 == 0) {
                return caminarIzquierda1;
            }

            return caminarIzquierda2;
        }

        if (entrada.teclaPresionada(teclaDerecha)) {

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

    	    batch.enableBlending();

    	    batch.begin();

    	    float offsetY = 0;

    	    if (this instanceof Gian) {
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

    public void dispose() {
        spriteSheet.dispose();
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
}