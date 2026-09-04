package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {

    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont fuente;

    private float tiempo;
    private int monedas;
    private boolean tieneLlave;

    public HUD() {

        camera =
            new OrthographicCamera();

        viewport =
            new FitViewport(
                960,
                640,
                camera
            );

        viewport.apply();

        camera.position.set(
            480,
            320,
            0
        );

        camera.update();

        fuente =
            new BitmapFont();

        fuente.getData().setScale(
            1.3f
        );

        tiempo = 0;

        monedas = 0;

        tieneLlave = false;
    }

    public void actualizar() {

        tiempo +=
            Gdx.graphics.getDeltaTime();
    }

    public void dibujar(
        SpriteBatch batch
    ) {

        viewport.apply();

        camera.update();

        batch.setProjectionMatrix(
            camera.combined
        );

        batch.begin();

        int segundosTotales =
            (int) tiempo;

        int minutos =
            segundosTotales / 60;

        int segundos =
            segundosTotales % 60;

        String tiempoTexto =
            String.format(
                "%02d:%02d",
                minutos,
                segundos
            );

        String llaveTexto;

        if (
            tieneLlave
        ) {

            llaveTexto = "SI";

        } else {

            llaveTexto = "NO";
        }

        fuente.draw(
            batch,
            "NIVEL 1",
            30,
            35
        );

        fuente.draw(
            batch,
            "MONEDAS: "
                + monedas
                + "/6",
            220,
            35
        );

        fuente.draw(
            batch,
            "LLAVE: "
                + llaveTexto,
            500,
            35
        );

        fuente.draw(
            batch,
            "TIEMPO: "
                + tiempoTexto,
            720,
            35
        );

        batch.end();
    }

    public void sumarMoneda() {

        if (
            monedas < 6
        ) {

            monedas++;
        }
    }

    public void obtenerLlave() {

        tieneLlave =
            true;
    }

    public void reiniciar() {

        tiempo = 0;

        monedas = 0;

        tieneLlave = false;
    }

    public int getMonedas() {

        return monedas;
    }

    public boolean tieneLlave() {

        return tieneLlave;
    }

    public float getTiempo() {

        return tiempo;
    }

    public void resize(
        int width,
        int height
    ) {

        viewport.update(
            width,
            height,
            true
        );

        camera.update();
    }

    public void dispose() {

        fuente.dispose();
    }
}