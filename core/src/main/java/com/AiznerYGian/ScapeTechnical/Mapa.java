package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mapa {

    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;

    public Mapa() {

        mapa = new TmxMapLoader().load(
            "mapas/MAPA NIVEL 1/NIVEL1_CANTINA.tmx"
        );

        renderer = new OrthogonalTiledMapRenderer(mapa);
    }

    public void dibujar(OrthographicCamera camera) {

        renderer.setView(camera);

        renderer.render();
    }

    public void dispose() {

        mapa.dispose();

        renderer.dispose();
    }
}