package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class Puerta {

    private TiledMapTileLayer capa;
    private Rectangle hitbox;

    private float yInicial;
    private float yAbierta;

    private float velocidad;
    private float tiempoAbierta;

    private Estado estado;

    private enum Estado {
        CERRADA,
        ABRIENDO,
        ABIERTA,
        CERRANDO
    }

    public Puerta(
        TiledMapTileLayer capa,
        Rectangle hitbox
    ) {

        this.capa = capa;
        this.hitbox = new Rectangle(hitbox);

        yInicial = hitbox.y;

        yAbierta =
            yInicial
            + hitbox.height
            + 20f;

        velocidad = 120f;

        tiempoAbierta = 0f;

        estado = Estado.CERRADA;
    }

    public void activar() {

        if (
            estado == Estado.CERRADA
        ) {

            System.out.println(
                "PUERTA COMIENZA A ABRIR"
            );

            estado = Estado.ABRIENDO;
        }
    }

    public void actualizar(
        float delta
    ) {

        if (
            estado == Estado.ABRIENDO
        ) {

            hitbox.y +=
                velocidad * delta;

            if (
                hitbox.y >= yAbierta
            ) {

                hitbox.y =
                    yAbierta;

                tiempoAbierta =
                    0f;

                estado =
                    Estado.ABIERTA;

                System.out.println(
                    "PUERTA ABIERTA"
                );
            }
        }

        else if (
            estado == Estado.ABIERTA
        ) {

            tiempoAbierta += delta;

            if (
                tiempoAbierta >= 7f
            ) {

                estado =
                    Estado.CERRANDO;

                System.out.println(
                    "PUERTA COMIENZA A CERRAR"
                );
            }
        }

        else if (
            estado == Estado.CERRANDO
        ) {

            hitbox.y -=
                velocidad * delta;

            if (
                hitbox.y <= yInicial
            ) {

                hitbox.y =
                    yInicial;

                estado =
                    Estado.CERRADA;

                System.out.println(
                    "PUERTA CERRADA"
                );
            }
        }

        capa.setOffsetY(
            yInicial - hitbox.y
        );
    }

    public Rectangle getHitbox() {

        return hitbox;
    }

    public boolean estaCerrada() {

        return
            estado
            == Estado.CERRADA;
    }

    public boolean estaAbierta() {

        return
            estado
            == Estado.ABIERTA;
    }

    public boolean bloqueaPaso() {

        return
            estado == Estado.CERRADA
            ||
            estado == Estado.ABRIENDO
            ||
            estado == Estado.CERRANDO;
    }

    public void reiniciar() {

        hitbox.y =
            yInicial;

        capa.setOffsetY(
            0
        );

        tiempoAbierta =
            0f;

        estado =
            Estado.CERRADA;
    }
}