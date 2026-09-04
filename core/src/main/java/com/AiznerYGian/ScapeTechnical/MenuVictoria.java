package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuVictoria {

    private Texture imagen;
    private BitmapFont fuente;

    private Rectangle botonMenuPrincipal;

    private float tiempoFinal;
    private int monedasFinales;

    public enum Accion {
        NINGUNA,
        MENU_PRINCIPAL
    }

    public MenuVictoria() {

        imagen = new Texture(
            "menus/menu_victoria.png"
        );

        fuente = new BitmapFont();

        fuente.getData().setScale(
            1.6f
        );

        botonMenuPrincipal =
            new Rectangle(
                255,
                125,
                450,
                75
            );
    }

    public void guardarResultado(
        float tiempo,
        int monedas
    ) {

        tiempoFinal = tiempo;
        monedasFinales = monedas;
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

        int totalSegundos =
            (int) tiempoFinal;

        int minutos =
            totalSegundos / 60;

        int segundos =
            totalSegundos % 60;

        String tiempo =
            String.format(
                "%02d:%02d",
                minutos,
                segundos
            );

        fuente.draw(
            batch,
            tiempo,
            600,
            377
        );

        fuente.draw(
            batch,
            monedasFinales + "/6",
            600,
            315
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
            botonMenuPrincipal.contains(
                mouse
            )
        ) {
            return Accion.MENU_PRINCIPAL;
        }

        return Accion.NINGUNA;
    }

    public void dispose() {
        imagen.dispose();
        fuente.dispose();
    }
}