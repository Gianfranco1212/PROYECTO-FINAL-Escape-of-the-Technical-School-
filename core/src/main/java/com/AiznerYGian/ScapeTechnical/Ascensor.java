package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class Ascensor {

    private TiledMapTileLayer capa;
    private Rectangle hitbox;

    private float yInicial;
    private float yDestino;

    private float velocidad;
    private float tiempoEspera;

    private Estado estado;

    private enum Estado {
        QUIETO,
        IDA,
        ESPERANDO,
        VUELTA
    }

    public Ascensor(
        TiledMapTileLayer capa,
        Rectangle hitbox,
        float yDestino
    ) {

        this.capa = capa;

        this.hitbox =
            new Rectangle(hitbox);

        this.yInicial =
            hitbox.y;

        this.yDestino =
            yDestino;

        velocidad =
            100f;

        tiempoEspera =
            0f;

        estado =
            Estado.QUIETO;
    }

    public void activar() {

        if (
            estado
            == Estado.QUIETO
        ) {

            estado =
                Estado.IDA;
        }
    }

    public float actualizar(
        float delta
    ) {

        float yAnterior =
            hitbox.y;

        if (
            estado
            == Estado.IDA
        ) {

            moverHacia(
                yDestino,
                delta
            );

            if (
                Math.abs(
                    hitbox.y
                    - yDestino
                ) < 0.5f
            ) {

                hitbox.y =
                    yDestino;

                tiempoEspera =
                    0f;

                estado =
                    Estado.ESPERANDO;
            }
        }

        else if (
            estado
            == Estado.ESPERANDO
        ) {

            tiempoEspera +=
                delta;

            if (
                tiempoEspera
                >= 2f
            ) {

                estado =
                    Estado.VUELTA;
            }
        }

        else if (
            estado
            == Estado.VUELTA
        ) {

            moverHacia(
                yInicial,
                delta
            );

            if (
                Math.abs(
                    hitbox.y
                    - yInicial
                ) < 0.5f
            ) {

                hitbox.y =
                    yInicial;

                estado =
                    Estado.QUIETO;
            }
        }

        capa.setOffsetY(
            yInicial
            - hitbox.y
        );

        return
            hitbox.y
            - yAnterior;
    }

    private void moverHacia(
        float destino,
        float delta
    ) {

        if (
            hitbox.y
            < destino
        ) {

            hitbox.y +=
                velocidad
                * delta;

            if (
                hitbox.y
                > destino
            ) {

                hitbox.y =
                    destino;
            }
        }

        else if (
            hitbox.y
            > destino
        ) {

            hitbox.y -=
                velocidad
                * delta;

            if (
                hitbox.y
                < destino
            ) {

                hitbox.y =
                    destino;
            }
        }
    }

    public Rectangle getHitbox() {

        return hitbox;
    }

    public boolean estaQuieto() {

        return
            estado
            == Estado.QUIETO;
    }

    public void reiniciar() {

        hitbox.y =
            yInicial;

        capa.setOffsetY(
            0
        );

        tiempoEspera =
            0f;

        estado =
            Estado.QUIETO;
    }
}