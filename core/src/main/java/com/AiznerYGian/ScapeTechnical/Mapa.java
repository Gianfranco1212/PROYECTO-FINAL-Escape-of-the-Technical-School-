package com.AiznerYGian.ScapeTechnical;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Mapa {

    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;

    private ArrayList<Rectangle> colisiones;

    private int anchoTile;
    private int altoTile;

    public Mapa() {

        mapa = new TmxMapLoader().load(
            "mapas/MAPA NIVEL 1/NIVEL1_CANTINA.tmx"
        );

        renderer = new OrthogonalTiledMapRenderer(mapa);

        anchoTile = mapa.getProperties().get(
            "tilewidth",
            Integer.class
        );

        altoTile = mapa.getProperties().get(
            "tileheight",
            Integer.class
        );

        cargarColisiones();
    }

    private void cargarColisiones() {

        colisiones = new ArrayList<>();

        MapLayer capaColisiones =
            mapa.getLayers().get("colisiones");

        if (capaColisiones == null) {

            System.out.println(
                "ERROR: No existe una capa llamada 'colisiones' en Tiled."
            );

            return;
        }

        for (MapObject objeto : capaColisiones.getObjects()) {

            if (objeto instanceof RectangleMapObject) {

                RectangleMapObject objetoRectangulo =
                    (RectangleMapObject) objeto;

                Rectangle rectangulo =
                    objetoRectangulo.getRectangle();

                colisiones.add(rectangulo);
            }
        }

        System.out.println(
            "Colisiones cargadas: " + colisiones.size()
        );
    }

    public ArrayList<Rectangle> getColisiones() {

        return colisiones;
    }

    public int getAnchoTile() {

        return anchoTile;
    }

    public int getAltoTile() {

        return altoTile;
    }

    public void dibujar(OrthographicCamera camera) {

        renderer.setView(camera);

        renderer.render();
    }

    public void dispose() {

        renderer.dispose();

        mapa.dispose();
    }
}