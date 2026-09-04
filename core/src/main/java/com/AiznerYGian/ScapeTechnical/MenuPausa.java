package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuPausa {

    private Texture imagenPausa;

    private Rectangle botonReanudar;
    private Rectangle botonReiniciar;
    private Rectangle botonAjustes;
    private Rectangle botonMenuPrincipal;

    public enum Accion {
        NINGUNA,
        REANUDAR,
        REINICIAR,
        AJUSTES,
        MENU_PRINCIPAL
    }

    public MenuPausa() {

        imagenPausa =
            new Texture(
                "menus/menu_pausa_nivel.png"
            );

        botonReanudar =
            new Rectangle(
                364,
                343,
                235,
                48
            );

        botonReiniciar =
            new Rectangle(
                364,
                291,
                235,
                46
            );

        botonAjustes =
            new Rectangle(
                364,
                238,
                235,
                46
            );

        botonMenuPrincipal =
            new Rectangle(
                364,
                180,
                235,
                48
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
            imagenPausa,
            174,
            116,
            612,
            408
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

            return Accion.NINGUNA;
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
            botonReanudar.contains(
                mouse
            )
        ) {
            return Accion.REANUDAR;
        }

        if (
            botonReiniciar.contains(
                mouse
            )
        ) {
            return Accion.REINICIAR;
        }

        if (
            botonAjustes.contains(
                mouse
            )
        ) {
            return Accion.AJUSTES;
        }

        if (
            botonMenuPrincipal.contains(
                mouse
            )
        ) {
            return Accion.MENU_PRINCIPAL;
        }

        return Accion.NINGUNA;
    }

    public void dispose() {
        imagenPausa.dispose();
    }
}