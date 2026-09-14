package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class MecanismosMapa {

    private MecanismoAscensores mecanismoAscensores;
    private MecanismoPuertas mecanismoPuertas;
    private MecanismoPuertaFinal mecanismoPuertaFinal;

    public MecanismosMapa(TiledMap mapa) {

        mecanismoAscensores =
            new MecanismoAscensores(
                mapa
            );

        mecanismoPuertas =
            new MecanismoPuertas(
                mapa
            );

        mecanismoPuertaFinal =
            new MecanismoPuertaFinal(
                mapa
            );
    }

    public void actualizar(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        mecanismoAscensores.actualizar(
            azn,
            gian,
            delta
        );

        mecanismoPuertas.actualizar(
            azn,
            gian,
            delta
        );
    }

    public void actualizarPuertaFinal(
        HUD hud
    ) {

        mecanismoPuertaFinal.actualizar(
            hud
        );
    }

    public boolean nivelCompletado(
        Personaje azn,
        Personaje gian,
        HUD hud
    ) {

        return
            mecanismoPuertaFinal
                .nivelCompletado(
                    azn,
                    gian,
                    hud
                );
    }

    public boolean colisionaConPuerta(
        Rectangle jugador
    ) {

        if (
            mecanismoPuertas
                .colisionaConPuerta(
                    jugador
                )
        ) {

            return true;
        }

        if (
            mecanismoPuertaFinal
                .colisionaConPuerta(
                    jugador
                )
        ) {

            return true;
        }

        return false;
    }

    public void dibujarPuertaFinal(
        SpriteBatch batch,
        OrthographicCamera camera
    ) {

        mecanismoPuertaFinal.dibujar(
            batch,
            camera
        );
    }

    public Rectangle
        getHitboxAscensorIzquierdo() {

        return
            mecanismoAscensores
                .getHitboxAscensorIzquierdo();
    }

    public Rectangle
        getHitboxAscensorDerecho() {

        return
            mecanismoAscensores
                .getHitboxAscensorDerecho();
    }

    public void dispose() {

        mecanismoPuertaFinal.dispose();
    }
}