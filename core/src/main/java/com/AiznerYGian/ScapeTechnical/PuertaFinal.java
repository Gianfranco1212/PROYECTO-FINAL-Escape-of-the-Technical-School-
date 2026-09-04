package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class PuertaFinal {

    private Texture imagenAbierta;
    private TiledMapTileLayer capaCerrada;
    private Rectangle hitbox;

    private boolean abierta;

    public PuertaFinal(
        TiledMapTileLayer capaCerrada,
        Rectangle hitbox
    ) {

        this.capaCerrada = capaCerrada;
        this.hitbox = new Rectangle(hitbox);

        imagenAbierta = new Texture(
        	    "mapas/MAPA NIVEL 1/puerta_final_abierta.png"
        	);

        abierta = false;
    }

    public void abrir() {

        if (abierta) {
            return;
        }

        abierta = true;

        capaCerrada.setVisible(false);

        System.out.println(
            "PUERTA FINAL ABIERTA"
        );
    }

    public boolean estaAbierta() {
        return abierta;
    }

    public boolean bloqueaPaso() {
        return !abierta;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void dibujar(
        SpriteBatch batch,
        OrthographicCamera camera
    ) {

        if (!abierta) {
            return;
        }

        batch.setProjectionMatrix(
            camera.combined
        );

        batch.begin();

        batch.draw(
            imagenAbierta,
            hitbox.x,
            hitbox.y,
            hitbox.width,
            hitbox.height
        );

        batch.end();
    }

    public void reiniciar() {

        abierta = false;

        capaCerrada.setVisible(true);
    }

    public void dispose() {

        imagenAbierta.dispose();
    }
}