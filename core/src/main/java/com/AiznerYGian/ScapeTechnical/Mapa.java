package com.AiznerYGian.ScapeTechnical;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Mapa {

    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;

    private int anchoTile;
    private int altoTile;

    private ColisionesMapa colisionesMapa;
    private MecanismosMapa mecanismosMapa;
    private RecolectablesMapa recolectablesMapa;

    public Mapa() {

        mapa =
            new TmxMapLoader().load(
                "mapas/MAPA NIVEL 1/NIVEL1_CANTINA.tmx"
            );

        renderer =
            new OrthogonalTiledMapRenderer(
                mapa
            );

        anchoTile =
            mapa.getProperties().get(
                "tilewidth",
                Integer.class
            );

        altoTile =
            mapa.getProperties().get(
                "tileheight",
                Integer.class
            );

        colisionesMapa =
            new ColisionesMapa(
                mapa
            );

        mecanismosMapa =
            new MecanismosMapa(
                mapa
            );

        recolectablesMapa =
            new RecolectablesMapa(
                mapa,
                anchoTile,
                altoTile
            );
    }

    public void actualizarMecanismos(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        mecanismosMapa.actualizar(
            azn,
            gian,
            delta
        );
    }

    public void actualizarRecolectables(
        Personaje azn,
        Personaje gian,
        HUD hud
    ) {

        recolectablesMapa.actualizar(
            azn,
            gian,
            hud
        );
    }

    public void actualizarPuertaFinal(
        HUD hud
    ) {

        mecanismosMapa.actualizarPuertaFinal(
            hud
        );
    }

    public boolean nivelCompletado(
        Personaje azn,
        Personaje gian,
        HUD hud
    ) {

        return mecanismosMapa.nivelCompletado(
            azn,
            gian,
            hud
        );
    }

    public boolean colisionaConPuerta(
        Rectangle jugador
    ) {

        return mecanismosMapa.colisionaConPuerta(
            jugador
        );
    }

    public void dibujarPuertaFinal(
        SpriteBatch batch,
        OrthographicCamera camera
    ) {

        mecanismosMapa.dibujarPuertaFinal(
            batch,
            camera
        );
    }

    public ArrayList<Rectangle> getColisiones() {

        return colisionesMapa.getColisiones();
    }

    public int getAnchoTile() {

        return anchoTile;
    }

    public int getAltoTile() {

        return altoTile;
    }

    public Rectangle getHitboxAscensorIzquierdo() {

        return mecanismosMapa
            .getHitboxAscensorIzquierdo();
    }

    public Rectangle getHitboxAscensorDerecho() {

        return mecanismosMapa
            .getHitboxAscensorDerecho();
    }

    public void dibujar(
        OrthographicCamera camera
    ) {

        renderer.setView(
            camera
        );

        renderer.render();
    }

    public void dispose() {

        mecanismosMapa.dispose();

        renderer.dispose();

        mapa.dispose();
    }
}