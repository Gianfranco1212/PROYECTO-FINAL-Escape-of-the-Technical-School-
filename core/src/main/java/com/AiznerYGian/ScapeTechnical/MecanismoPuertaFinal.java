package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class MecanismoPuertaFinal {

    private PuertaFinal puertaFinal;

    private Rectangle zonaSalida;

    public MecanismoPuertaFinal(
        TiledMap mapa
    ) {

        cargar(
            mapa
        );
    }

    private void cargar(
        TiledMap mapa
    ) {

        MapLayer capa =
            mapa.getLayers().get(
                "Mecanismos"
            );

        if (
            capa == null
        ) {

            return;
        }

        Rectangle hitboxFinal =
            null;

        for (
            MapObject objeto :
            capa.getObjects()
        ) {

            if (
                !(objeto instanceof
                RectangleMapObject)
            ) {

                continue;
            }

            String nombre =
                objeto.getName();

            if (
                nombre == null
            ) {

                continue;
            }

            Rectangle rect =
                ((RectangleMapObject) objeto)
                    .getRectangle();

            if (
                nombre.equals(
                    "puerta_final_hitbox"
                )
            ) {

                hitboxFinal =
                    new Rectangle(
                        rect
                    );
            }

            else if (
                nombre.equals(
                    "zona_salida"
                )
            ) {

                zonaSalida =
                    new Rectangle(
                        rect
                    );
            }
        }

        TiledMapTileLayer capaFinal =
            (TiledMapTileLayer)
            mapa.getLayers().get(
                "puerta_final"
            );

        if (
            capaFinal != null
            &&
            hitboxFinal != null
        ) {

            puertaFinal =
                new PuertaFinal(
                    capaFinal,
                    hitboxFinal
                );
        }
    }

    public void actualizar(
        HUD hud
    ) {

        if (
            puertaFinal == null
        ) {

            return;
        }

        if (
            hud.tieneLlave()
            &&
            !puertaFinal.estaAbierta()
        ) {

            puertaFinal.abrir();
        }
    }

    public boolean nivelCompletado(
        Personaje azn,
        Personaje gian,
        HUD hud
    ) {

        if (
            puertaFinal == null
            ||
            zonaSalida == null
            ||
            !hud.tieneLlave()
            ||
            !puertaFinal.estaAbierta()
        ) {

            return false;
        }

        boolean aznEnMeta =
            azn.getHitbox()
                .overlaps(
                    zonaSalida
                );

        boolean gianEnMeta =
            gian.getHitbox()
                .overlaps(
                    zonaSalida
                );

        return
            aznEnMeta
            &&
            gianEnMeta;
    }

    public boolean colisionaConPuerta(
        Rectangle jugador
    ) {

        if (
            puertaFinal != null
            &&
            puertaFinal.bloqueaPaso()
            &&
            jugador.overlaps(
                puertaFinal.getHitbox()
            )
        ) {

            return true;
        }

        return false;
    }

    public void dibujar(
        SpriteBatch batch,
        OrthographicCamera camera
    ) {

        if (
            puertaFinal != null
        ) {

            puertaFinal.dibujar(
                batch,
                camera
            );
        }
    }

    public void dispose() {

        if (
            puertaFinal != null
        ) {

            puertaFinal.dispose();
        }
    }
}