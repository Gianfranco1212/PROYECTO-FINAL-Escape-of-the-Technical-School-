package com.AiznerYGian.ScapeTechnical;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class ColisionesMapa {

    private ArrayList<Rectangle> colisiones;

    public ColisionesMapa(
        TiledMap mapa
    ) {

        colisiones =
            new ArrayList<>();

        cargar(
            mapa
        );
    }

    private void cargar(
        TiledMap mapa
    ) {

        MapLayer capa =
            mapa.getLayers().get(
                "colisiones"
            );

        if (
            capa == null
        ) {

            return;
        }

        for (
            MapObject objeto :
            capa.getObjects()
        ) {

            if (
                objeto instanceof
                RectangleMapObject
            ) {

                colisiones.add(
                    new Rectangle(
                        ((RectangleMapObject) objeto)
                            .getRectangle()
                    )
                );
            }
        }
    }

    public ArrayList<Rectangle>
        getColisiones() {

        return colisiones;
    }
}