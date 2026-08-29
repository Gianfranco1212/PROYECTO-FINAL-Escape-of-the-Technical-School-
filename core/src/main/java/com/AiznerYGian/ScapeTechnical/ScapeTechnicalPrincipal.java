package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScapeTechnicalPrincipal extends ApplicationAdapter {

    private Mapa mapa;

    private Personaje azn;
    private Personaje gian;

    private MenuInicio menu;

    private Entrada entrada;

    private OrthographicCamera camera;

    private SpriteBatch batch;

    @Override
    public void create() {

        camera = new OrthographicCamera();

        camera.setToOrtho(
            false,
            960,
            640
        );

        batch = new SpriteBatch();

        entrada = new Entrada();

        mapa = new Mapa();

        azn = new Azn(mapa);

        gian = new Gian(mapa);

        menu = new MenuInicio();
    }

    @Override
    public void render() {

        ScreenUtils.clear(
            0.15f,
            0.15f,
            0.2f,
            1f
        );

        camera.update();

        if (menu.estaActivo()) {

            menu.dibujar(
                batch,
                camera
            );

            if (
                entrada.teclaJustoPresionada(
                    com.badlogic.gdx.Input.Keys.ENTER
                )
            ) {
                menu.cerrar();
            }

            return;
        }

        azn.actualizar(
        	    entrada,
        	    gian
        	);

        	gian.actualizar(
        	    entrada,
        	    azn
        	);
        mapa.dibujar(camera);

        azn.dibujar(
            batch,
            camera
        );

        gian.dibujar(
            batch,
            camera
        );
    }

    @Override
    public void dispose() {

        mapa.dispose();

        azn.dispose();

        gian.dispose();

        menu.dispose();

        entrada.dispose();

        batch.dispose();
    }
}