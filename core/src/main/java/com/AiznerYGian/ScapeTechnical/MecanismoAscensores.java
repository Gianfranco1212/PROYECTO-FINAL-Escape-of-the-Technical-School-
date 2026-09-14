package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class MecanismoAscensores {

    private Rectangle botonAscensores;

    private Ascensor ascensorIzquierdo;
    private Ascensor ascensorDerecho;

    private boolean botonEstabaPulsado;

    public MecanismoAscensores(
        TiledMap mapa
    ) {

        cargar(
            mapa
        );

        botonEstabaPulsado =
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

        Rectangle hitboxIzq =
            null;

        Rectangle hitboxDer =
            null;

        Float destinoIzq =
            null;

        Float destinoDer =
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
                    "boton_ascensores"
                )
            ) {

                botonAscensores =
                    new Rectangle(
                        rect
                    );
            }

            else if (
                nombre.equals(
                    "ascensor_izq_hitbox"
                )
            ) {

                hitboxIzq =
                    new Rectangle(
                        rect
                    );
            }

            else if (
                nombre.equals(
                    "ascensor_der_hitbox"
                )
            ) {

                hitboxDer =
                    new Rectangle(
                        rect
                    );
            }

            else if (
                nombre.equals(
                    "destino_ascensor_izq"
                )
            ) {

                destinoIzq =
                    rect.y;
            }

            else if (
                nombre.equals(
                    "destino_ascensor_der"
                )
            ) {

                destinoDer =
                    rect.y;
            }
        }

        TiledMapTileLayer capaAscensorIzq =
            (TiledMapTileLayer)
            mapa.getLayers().get(
                "ascensor_izq"
            );

        TiledMapTileLayer capaAscensorDer =
            (TiledMapTileLayer)
            mapa.getLayers().get(
                "ascensor_der"
            );

        if (
            capaAscensorIzq != null
            &&
            hitboxIzq != null
            &&
            destinoIzq != null
        ) {

            ascensorIzquierdo =
                new Ascensor(
                    capaAscensorIzq,
                    hitboxIzq,
                    destinoIzq
                );
        }

        if (
            capaAscensorDer != null
            &&
            hitboxDer != null
            &&
            destinoDer != null
        ) {

            ascensorDerecho =
                new Ascensor(
                    capaAscensorDer,
                    hitboxDer,
                    destinoDer
                );
        }
    }

    public void actualizar(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        if (
            botonAscensores == null
            ||
            ascensorIzquierdo == null
            ||
            ascensorDerecho == null
        ) {

            return;
        }

        boolean pulsado =
            azn.getHitbox()
                .overlaps(
                    botonAscensores
                )
            ||
            gian.getHitbox()
                .overlaps(
                    botonAscensores
                );

        if (
            pulsado
            &&
            !botonEstabaPulsado
            &&
            ascensorIzquierdo
                .estaQuieto()
            &&
            ascensorDerecho
                .estaQuieto()
        ) {

            ascensorIzquierdo
                .activar();

            ascensorDerecho
                .activar();
        }

        botonEstabaPulsado =
            pulsado;

        actualizarAscensor(
            ascensorIzquierdo,
            azn,
            gian,
            delta
        );

        actualizarAscensor(
            ascensorDerecho,
            azn,
            gian,
            delta
        );
    }

    private void actualizarAscensor(
        Ascensor ascensor,
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        boolean aznArriba =
            estaSobreAscensor(
                azn,
                ascensor
            );

        boolean gianArriba =
            estaSobreAscensor(
                gian,
                ascensor
            );

        float movimiento =
            ascensor.actualizar(
                delta
            );

        if (
            aznArriba
        ) {

            azn.moverConAscensor(
                movimiento
            );
        }

        if (
            gianArriba
        ) {

            gian.moverConAscensor(
                movimiento
            );
        }
    }

    private boolean estaSobreAscensor(
        Personaje personaje,
        Ascensor ascensor
    ) {

        Rectangle jugador =
            personaje.getHitbox();

        Rectangle plataforma =
            ascensor.getHitbox();

        boolean horizontal =
            jugador.x
            + jugador.width
            > plataforma.x
            &&
            jugador.x
            < plataforma.x
            + plataforma.width;

        float pies =
            jugador.y;

        float arriba =
            plataforma.y
            + plataforma.height;

        return
            horizontal
            &&
            Math.abs(
                pies - arriba
            ) <= 8f;
    }

    public Rectangle
        getHitboxAscensorIzquierdo() {

        if (
            ascensorIzquierdo == null
        ) {

            return null;
        }

        return
            ascensorIzquierdo
                .getHitbox();
    }

    public Rectangle
        getHitboxAscensorDerecho() {

        if (
            ascensorDerecho == null
        ) {

            return null;
        }

        return
            ascensorDerecho
                .getHitbox();
    }
}