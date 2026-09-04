package com.AiznerYGian.ScapeTechnical;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Mapa {

    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;

    private ArrayList<Rectangle> colisiones;

    private int anchoTile;
    private int altoTile;

    private Rectangle botonAscensores;

    private Ascensor ascensorIzquierdo;
    private Ascensor ascensorDerecho;

    private TiledMapTileLayer capaMonedas;
    private TiledMapTileLayer capaLlave;

    private boolean botonEstabaPulsado;

    private Rectangle botonAzul;
    private Rectangle botonRojo;

    private Puerta puertaAzul;
    private Puerta puertaRoja;

    private boolean botonAzulEstabaPulsado;
    private boolean botonRojoEstabaPulsado;

    private PuertaFinal puertaFinal;

    private Rectangle zonaSalida;

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

        cargarColisiones();

        cargarMecanismos();

        cargarRecolectables();

        botonEstabaPulsado = false;

        botonAzulEstabaPulsado = false;

        botonRojoEstabaPulsado = false;
    }

    private void cargarColisiones() {

        colisiones =
            new ArrayList<>();

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

    private void cargarMecanismos() {

        MapLayer capa =
            mapa.getLayers().get(
                "Mecanismos"
            );

        if (
            capa == null
        ) {

            return;
        }

        Rectangle hitboxIzq = null;
        Rectangle hitboxDer = null;

        Float destinoIzq = null;
        Float destinoDer = null;

        Rectangle hitboxAzul = null;
        Rectangle hitboxRoja = null;

        Rectangle hitboxFinal = null;

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
                    new Rectangle(rect);
            }

            else if (
                nombre.equals(
                    "ascensor_izq_hitbox"
                )
            ) {

                hitboxIzq =
                    new Rectangle(rect);
            }

            else if (
                nombre.equals(
                    "ascensor_der_hitbox"
                )
            ) {

                hitboxDer =
                    new Rectangle(rect);
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

            else if (
                nombre.equals(
                    "boton_azul"
                )
            ) {

                botonAzul =
                    new Rectangle(rect);
            }

            else if (
                nombre.equals(
                    "boton_rojo"
                )
            ) {

                botonRojo =
                    new Rectangle(rect);
            }

            else if (
                nombre.equals(
                    "puerta_azul_hitbox"
                )
            ) {

                hitboxAzul =
                    new Rectangle(rect);
            }

            else if (
                nombre.equals(
                    "puerta_roja_hitbox"
                )
            ) {

                hitboxRoja =
                    new Rectangle(rect);
            }

            else if (
                nombre.equals(
                    "puerta_final_hitbox"
                )
            ) {

                hitboxFinal =
                    new Rectangle(rect);
            }

            else if (
                nombre.equals(
                    "zona_salida"
                )
            ) {

                zonaSalida =
                    new Rectangle(rect);
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

    private void cargarRecolectables() {

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

    public void actualizarMecanismos(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        actualizarAscensores(
            azn,
            gian,
            delta
        );

        actualizarPuertas(
            azn,
            gian,
            delta
        );
    }

    private void actualizarAscensores(
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

            ascensorIzquierdo.activar();

            ascensorDerecho.activar();
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

    private void actualizarPuertas(
        Personaje azn,
        Personaje gian,
        float delta
    ) {

        if (
            botonAzul != null
            &&
            puertaAzul != null
        ) {

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

        if (
            botonRojo != null
            &&
            puertaRoja != null
        ) {

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
    }

    public void actualizarRecolectables(
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
                (int)(
                    jugador.x
                    / anchoTile
                )
            );

        int finX =
            Math.min(
                capaMonedas.getWidth()
                - 1,
                (int)(
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
                (int)(
                    jugador.y
                    / altoTile
                )
            );

        int finY =
            Math.min(
                capaMonedas.getHeight()
                - 1,
                (int)(
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

    public void actualizarPuertaFinal(
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
            azn.getHitbox().overlaps(
                zonaSalida
            );

        boolean gianEnMeta =
            gian.getHitbox().overlaps(
                zonaSalida
            );

        if (
            aznEnMeta
            &&
            gianEnMeta
        ) {

            return true;
        }

        return false;
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

    public void dibujarPuertaFinal(
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

    public ArrayList<Rectangle>
        getColisiones() {

        return colisiones;
    }

    public int getAnchoTile() {
        return anchoTile;
    }

    public int getAltoTile() {
        return altoTile;
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

    public void dibujar(
        OrthographicCamera camera
    ) {

        renderer.setView(
            camera
        );

        renderer.render();
    }

    public void dispose() {

        if (
            puertaFinal != null
        ) {
            puertaFinal.dispose();
        }

        renderer.dispose();

        mapa.dispose();
    }
}