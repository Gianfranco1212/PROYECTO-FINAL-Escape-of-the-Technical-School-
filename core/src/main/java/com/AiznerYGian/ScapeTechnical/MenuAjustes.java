package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuAjustes {

    public enum Origen {
        MENU_INICIO,
        MENU_PAUSA
    }

    public enum Accion {
        NINGUNA,
        VOLVER
    }

    private Texture imagen;

    private Audio audio;

    private Rectangle barraSonido;
    private Rectangle barraMusica;

    private Rectangle botonVolver;
    private Rectangle botonRestablecer;

    private ShapeRenderer shapeRenderer;

    private Origen origen;

    private boolean arrastrandoSonido;
    private boolean arrastrandoMusica;

    public MenuAjustes(
        Audio audio
    ) {

        this.audio = audio;

        imagen =
            new Texture(
                "menus/menuAjustes.png"
            );

        barraSonido =
            new Rectangle(
                478,
                405,
                170,
                24
            );

        barraMusica =
            new Rectangle(
                478,
                329,
                170,
                24
            );

        botonVolver =
            new Rectangle(
                300,
                232,
                365,
                58
            );

        botonRestablecer =
            new Rectangle(
                300,
                158,
                365,
                58
            );

        shapeRenderer =
            new ShapeRenderer();

        origen =
            Origen.MENU_INICIO;

        arrastrandoSonido =
            false;

        arrastrandoMusica =
            false;
    }

    public void abrirDesde(
        Origen origen
    ) {

        this.origen =
            origen;

        arrastrandoSonido =
            false;

        arrastrandoMusica =
            false;
    }

    public Origen getOrigen() {

        return origen;
    }

    public void dibujar(
        SpriteBatch batch,
        OrthographicCamera camera
    ) {

        batch.setProjectionMatrix(
            camera.combined
        );

        batch.begin();

        batch.draw(
            imagen,
            0,
            0,
            960,
            640
        );

        batch.end();

        dibujarControles(
            camera
        );
    }

    private void dibujarControles(
        OrthographicCamera camera
    ) {

        shapeRenderer.setProjectionMatrix(
            camera.combined
        );

        shapeRenderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        float volumenSonido =
            audio.getVolumenEfectos();

        float volumenMusica =
            audio.getVolumenMusica();

        float xSonido =
            barraSonido.x
            +
            barraSonido.width
            * volumenSonido;

        float xMusica =
            barraMusica.x
            +
            barraMusica.width
            * volumenMusica;

        shapeRenderer.setColor(
            0.2f,
            0.85f,
            0.05f,
            1f
        );

        shapeRenderer.rect(
            barraSonido.x,
            barraSonido.y
            + 7,
            barraSonido.width
            * volumenSonido,
            10
        );

        shapeRenderer.rect(
            barraMusica.x,
            barraMusica.y
            + 7,
            barraMusica.width
            * volumenMusica,
            10
        );

        shapeRenderer.setColor(
            0.8f,
            0.85f,
            0.9f,
            1f
        );

        shapeRenderer.rect(
            xSonido - 7,
            barraSonido.y - 3,
            14,
            30
        );

        shapeRenderer.rect(
            xMusica - 7,
            barraMusica.y - 3,
            14,
            30
        );

        shapeRenderer.end();
    }

    public Accion actualizar(
        Viewport viewport
    ) {

        Vector2 mouse =
            new Vector2(
                Gdx.input.getX(),
                Gdx.input.getY()
            );

        viewport.unproject(
            mouse
        );

        if (
            Gdx.input.isButtonJustPressed(
                Input.Buttons.LEFT
            )
        ) {

            if (
                barraSonido.contains(
                    mouse
                )
            ) {

                arrastrandoSonido =
                    true;

                cambiarVolumenSonido(
                    mouse.x
                );
            }

            else if (
                barraMusica.contains(
                    mouse
                )
            ) {

                arrastrandoMusica =
                    true;

                cambiarVolumenMusica(
                    mouse.x
                );
            }

            else if (
                botonVolver.contains(
                    mouse
                )
            ) {

                return Accion.VOLVER;
            }

            else if (
                botonRestablecer.contains(
                    mouse
                )
            ) {

                restablecerConfiguracion();
            }
        }

        if (
            Gdx.input.isButtonPressed(
                Input.Buttons.LEFT
            )
        ) {

            if (
                arrastrandoSonido
            ) {

                cambiarVolumenSonido(
                    mouse.x
                );
            }

            if (
                arrastrandoMusica
            ) {

                cambiarVolumenMusica(
                    mouse.x
                );
            }

        } else {

            arrastrandoSonido =
                false;

            arrastrandoMusica =
                false;
        }

        return Accion.NINGUNA;
    }

    private void cambiarVolumenSonido(
        float mouseX
    ) {

        float volumen =
            (
                mouseX
                - barraSonido.x
            )
            /
            barraSonido.width;

        volumen =
            MathUtils.clamp(
                volumen,
                0f,
                1f
            );

        audio.setVolumenEfectos(
            volumen
        );
    }

    private void cambiarVolumenMusica(
        float mouseX
    ) {

        float volumen =
            (
                mouseX
                - barraMusica.x
            )
            /
            barraMusica.width;

        volumen =
            MathUtils.clamp(
                volumen,
                0f,
                1f
            );

        audio.setVolumenMusica(
            volumen
        );
    }

    private void restablecerConfiguracion() {

        audio.setVolumenEfectos(
            0.70f
        );

        audio.setVolumenMusica(
            0.50f
        );
    }

    public void dispose() {

        imagen.dispose();

        shapeRenderer.dispose();
    }
}