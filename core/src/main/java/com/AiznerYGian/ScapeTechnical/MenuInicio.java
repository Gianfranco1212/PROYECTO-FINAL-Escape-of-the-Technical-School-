package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuInicio {

    private BitmapFont fuente;

    private boolean activo;

    public MenuInicio() {

        fuente = new BitmapFont();

        activo = true;
    }

    public void dibujar(
        SpriteBatch batch,
        OrthographicCamera camera
    ) {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        fuente.draw(
            batch,
            "ESCAPE OF THE TECHNICAL SCHOOL",
            300,
            400
        );

        fuente.draw(
            batch,
            "Presiona ENTER para jugar",
            360,
            320
        );

        batch.end();
    }

    public boolean estaActivo() {

        return activo;
    }

    public void cerrar() {

        activo = false;
    }

    public void dispose() {

        fuente.dispose();
    }
}