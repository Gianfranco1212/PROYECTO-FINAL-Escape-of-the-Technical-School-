package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class MecanismoPuertas {

    private Rectangle botonAzul;
    private Rectangle botonRojo;

    private Puerta puertaAzul;
    private Puerta puertaRoja;

    private boolean botonAzulEstabaPulsado;
    private boolean botonRojoEstabaPulsado;

    public MecanismoPuertas(
        TiledMap mapa
    ) {

        cargar(
            mapa
        );

        botonAzulEstabaPulsado =
            false;

        botonRojoEstabaPulsado =
            false;
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

        Rectangle hitboxAzul =
            null;

        Rectangle hitboxRoja =
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
                    "boton_azul"
                )
            ) {

                botonAzul =
                    new Rectangle(
                        rect
                    );
            }

            else if (
                nombre.equals(
                    "boton_rojo"
                )
            ) {

                botonRojo =
                    new Rectangle(
                        rect
                    );
            }

            else if (
                nombre.equals(
                    "puerta_azul_hitbox"
                )
            ) {

                hitboxAzul =
                    new Rectangle(
                        rect
                    );
            }

            else if (
                nombre.equals(
                    "puerta_roja_hitbox"
                )
            ) {

                hitboxRoja =
                    new Rectangle(
                        rect
                    );
            }
        }

        TiledMapTileLayer capaAzul =
            (TiledMapTileLayer)
            mapa.getLayers().get(
                "puerta_azul"
            );

        TiledMapTileLayer capaRoja =
            (TiledMapTileLayer)
            mapa.getLayers().get(
                "puerta_roja"
            );

        if (
            capaAzul != null
            &&
            hitboxAzul != null
        ) {

            puertaAzul =
                new Puerta(
                    capaAzul,
                    hitboxAzul
                );
        }

        if (
            capaRoja != null
            &&
            hitboxRoja != null
        ) {

            puertaRoja =
                new Puerta(
                    capaRoja,
                    hitboxRoja
                );
        }
    }

    public void actualizar(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        actualizarPuertaAzul(
            azn,
            gian,
            delta
        );

        actualizarPuertaRoja(
            azn,
            gian,
            delta
        );
    }

    private void actualizarPuertaAzul(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        if (
            botonAzul == null
            ||
            puertaAzul == null
        ) {

            return;
        }

        boolean pulsado =
            estaPisando(
                azn,
                botonAzul
            )
            ||
            estaPisando(
                gian,
                botonAzul
            );

        if (
            pulsado
            &&
            !botonAzulEstabaPulsado
            &&
            puertaAzul.estaCerrada()
        ) {

            puertaAzul.activar();
        }

        botonAzulEstabaPulsado =
            pulsado;

        puertaAzul.actualizar(
            delta
        );
    }

    private void actualizarPuertaRoja(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        if (
            botonRojo == null
            ||
            puertaRoja == null
        ) {

            return;
        }

        boolean pulsado =
            estaPisando(
                azn,
                botonRojo
            )
            ||
            estaPisando(
                gian,
                botonRojo
            );

        if (
            pulsado
            &&
            !botonRojoEstabaPulsado
            &&
            puertaRoja.estaCerrada()
        ) {

            puertaRoja.activar();
        }

        botonRojoEstabaPulsado =
            pulsado;

        puertaRoja.actualizar(
            delta
        );
    }

    private boolean estaPisando(
        Personaje personaje,
        Rectangle boton
    ) {

        if (
            boton == null
        ) {

            return false;
        }

        Rectangle jugador =
            personaje.getHitbox();

        boolean horizontal =
            jugador.x
            + jugador.width
            > boton.x
            &&
            jugador.x
            < boton.x
            + boton.width;

        return
            horizontal
            &&
            Math.abs(
                jugador.y
                -
                (
                    boton.y
                    + boton.height
                )
            ) <= 12f;
    }

    public boolean colisionaConPuerta(
        Rectangle jugador
    ) {

        if (
            puertaAzul != null
            &&
            puertaAzul.bloqueaPaso()
            &&
            jugador.overlaps(
                puertaAzul.getHitbox()
            )
        ) {

            return true;
        }

        if (
            puertaRoja != null
            &&
            puertaRoja.bloqueaPaso()
            &&
            jugador.overlaps(
                puertaRoja.getHitbox()
            )
        ) {

            return true;
        }

        return false;
    }
}