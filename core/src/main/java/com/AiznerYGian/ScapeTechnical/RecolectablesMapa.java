package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class RecolectablesMapa {

    private TiledMapTileLayer capaMonedas;
    private TiledMapTileLayer capaLlave;

    private int anchoTile;
    private int altoTile;

    public RecolectablesMapa(
        TiledMap mapa,
        int anchoTile,
        int altoTile
    ) {

        this.anchoTile =
            anchoTile;

        this.altoTile =
            altoTile;

        capaMonedas =
            (TiledMapTileLayer)
            mapa.getLayers().get(
                "monedas"
            );

        capaLlave =
            (TiledMapTileLayer)
            mapa.getLayers().get(
                "llave"
            );
    }

    public void actualizar(
        Personaje azn,
        Personaje gian,
        HUD hud
    ) {

        recogerMonedas(
            azn,
            hud
        );

        recogerMonedas(
            gian,
            hud
        );

        recogerLlave(
            azn,
            hud
        );

        recogerLlave(
            gian,
            hud
        );
    }

    private void recogerMonedas(
        Personaje personaje,
        HUD hud
    ) {

        if (
            capaMonedas == null
        ) {

            return;
        }

        Rectangle jugador =
            personaje.getHitbox();

        int inicioX =
            Math.max(
                0,
                (int)
                (
                    jugador.x
                    / anchoTile
                )
            );

        int finX =
            Math.min(
                capaMonedas.getWidth()
                - 1,
                (int)
                (
                    (
                        jugador.x
                        + jugador.width
                    )
                    / anchoTile
                )
            );

        int inicioY =
            Math.max(
                0,
                (int)
                (
                    jugador.y
                    / altoTile
                )
            );

        int finY =
            Math.min(
                capaMonedas.getHeight()
                - 1,
                (int)
                (
                    (
                        jugador.y
                        + jugador.height
                    )
                    / altoTile
                )
            );

        for (
            int x = inicioX;
            x <= finX;
            x++
        ) {

            for (
                int y = inicioY;
                y <= finY;
                y++
            ) {

                TiledMapTileLayer.Cell celda =
                    capaMonedas.getCell(
                        x,
                        y
                    );

                if (
                    celda == null
                ) {

                    continue;
                }

                Rectangle moneda =
                    new Rectangle(
                        x * anchoTile,
                        y * altoTile,
                        anchoTile,
                        altoTile
                    );

                if (
                    jugador.overlaps(
                        moneda
                    )
                ) {

                    capaMonedas.setCell(
                        x,
                        y,
                        null
                    );

                    hud.sumarMoneda();
                }
            }
        }
    }

    private void recogerLlave(
        Personaje personaje,
        HUD hud
    ) {

        if (
            capaLlave == null
            ||
            hud.tieneLlave()
        ) {

            return;
        }

        Rectangle jugador =
            personaje.getHitbox();

        for (
            int x = 0;
            x < capaLlave.getWidth();
            x++
        ) {

            for (
                int y = 0;
                y < capaLlave.getHeight();
                y++
            ) {

                TiledMapTileLayer.Cell celda =
                    capaLlave.getCell(
                        x,
                        y
                    );

                if (
                    celda == null
                ) {

                    continue;
                }

                Rectangle llave =
                    new Rectangle(
                        x * anchoTile,
                        y * altoTile,
                        anchoTile,
                        altoTile
                    );

                if (
                    jugador.overlaps(
                        llave
                    )
                ) {

                    capaLlave.setCell(
                        x,
                        y,
                        null
                    );

                    hud.obtenerLlave();

                    return;
                }
            }
        }
    }
}