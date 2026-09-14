package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuInicio {

    private Texture imagenMenu;

    private boolean activo;

    private Rectangle botonJugar;
    private Rectangle botonAjustes;
    private Rectangle botonSalir;

    public enum Accion {
        NINGUNA,
        JUGAR,
        AJUSTES,
        SALIR
    }

    public MenuInicio() {

        imagenMenu =
            new Texture(
                "menus/menuInicio.png"
            );

        activo =
            true;

        botonJugar =
            new Rectangle(
                322,
                275,
                287,
                65
            );

        botonAjustes =
            new Rectangle(
                322,
                200,
                287,
                65
            );

        botonSalir =
            new Rectangle(
                322,
                125,
                287,
                65
            );
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
            imagenMenu,
            0,
            39,
            960,
            562
        );

        batch.end();
    }

    public Accion procesarClick(
        Viewport viewport
    ) {

        if (
            !Gdx.input.isButtonJustPressed(
                Input.Buttons.LEFT
            )
        ) {

            return
                Accion.NINGUNA;
        }

        Vector2 mouse =
            new Vector2(
                Gdx.input.getX(),
                Gdx.input.getY()
            );

        viewport.unproject(
            mouse
        );

        if (
            botonJugar.contains(
                mouse
            )
        ) {

            return
                Accion.JUGAR;
        }

        if (
            botonAjustes.contains(
                mouse
            )
        ) {

            return
                Accion.AJUSTES;
        }

        if (
            botonSalir.contains(
                mouse
            )
        ) {

            return
                Accion.SALIR;
        }

        return
            Accion.NINGUNA;
    }

    public boolean estaActivo() {

        return activo;
    }

    public void cerrar() {

        activo = false;
    }

    public void abrir() {

        activo = true;
    }

    public void dispose() {

        imagenMenu.dispose();
    }
}