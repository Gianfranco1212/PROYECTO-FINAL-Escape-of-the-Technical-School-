package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScapeTechnicalPrincipal
    extends ApplicationAdapter {

    private Mapa mapa;

    private Personaje azn;
    private Personaje gian;

    private MenuInicio menuInicio;
    private MenuPausa menuPausa;
    private MenuAjustes menuAjustes;
    private MenuVictoria menuVictoria;

    private Entrada entrada;

    private HUD hud;

    private Audio audio;

    private OrthographicCamera camera;

    private Viewport viewport;

    private SpriteBatch batch;

    private boolean pausado;
    private boolean enAjustes;
    private boolean victoria;

    @Override
    public void create() {

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

        batch =
            new SpriteBatch();

        entrada =
            new Entrada();

        audio =
            new Audio();

        menuInicio =
            new MenuInicio();

        menuPausa =
            new MenuPausa();

        menuAjustes =
            new MenuAjustes(
                audio
            );

        menuVictoria =
            new MenuVictoria();

        crearNivel();

        pausado = false;

        enAjustes = false;

        victoria = false;
    }

    private void crearNivel() {

        mapa =
            new Mapa();

        azn =
            new Azn(
                mapa,
                audio
            );

        gian =
            new Gian(
                mapa,
                audio
            );

        hud =
            new HUD();

        hud.resize(
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
    }

    private void destruirNivel() {

        audio.detenerTodosLosPasos();

        if (
            azn != null
        ) {
            azn.dispose();
        }

        if (
            gian != null
        ) {
            gian.dispose();
        }

        if (
            mapa != null
        ) {
            mapa.dispose();
        }

        if (
            hud != null
        ) {
            hud.dispose();
        }
    }

    private void reiniciarNivel() {

        audio.detenerTodosLosPasos();

        audio.detenerMusicaNivel();

        destruirNivel();

        crearNivel();

        pausado = false;

        enAjustes = false;

        victoria = false;

        audio.reproducirMusicaNivel();
    }

    @Override
    public void render() {

        ScreenUtils.clear(
            0.15f,
            0.15f,
            0.2f,
            1f
        );

        viewport.apply();

        camera.update();

        if (
            victoria
        ) {

            menuVictoria.dibujar(
                batch,
                camera
            );

            MenuVictoria.Accion accion =
                menuVictoria.procesarClick(
                    viewport
                );

            if (
                accion
                ==
                MenuVictoria.Accion
                    .MENU_PRINCIPAL
            ) {

                victoria = false;

                menuInicio.abrir();

                audio.detenerTodosLosPasos();

                audio.detenerMusicaNivel();
            }

            return;
        }

        if (
            enAjustes
        ) {

            menuAjustes.dibujar(
                batch,
                camera
            );

            MenuAjustes.Accion accion =
                menuAjustes.actualizar(
                    viewport
                );

            if (
                accion
                ==
                MenuAjustes.Accion.VOLVER
            ) {

                enAjustes = false;

                if (
                    menuAjustes.getOrigen()
                    ==
                    MenuAjustes.Origen
                        .MENU_INICIO
                ) {

                    menuInicio.abrir();

                } else {

                    pausado = true;
                }
            }

            return;
        }

        if (
            menuInicio.estaActivo()
        ) {

            menuInicio.dibujar(
                batch,
                camera
            );

            MenuInicio.Accion accion =
                menuInicio.procesarClick(
                    viewport
                );

            if (
                accion
                ==
                MenuInicio.Accion.JUGAR
            ) {

                reiniciarNivel();

                menuInicio.cerrar();
            }

            else if (
                accion
                ==
                MenuInicio.Accion.AJUSTES
            ) {

                menuInicio.cerrar();

                enAjustes = true;

                menuAjustes.abrirDesde(
                    MenuAjustes.Origen
                        .MENU_INICIO
                );
            }

            else if (
                accion
                ==
                MenuInicio.Accion.SALIR
            ) {

                Gdx.app.exit();
            }

            return;
        }

        if (
            Gdx.input.isKeyJustPressed(
                Input.Keys.ESCAPE
            )
        ) {

            pausado =
                !pausado;

            if (
                pausado
            ) {

                audio.detenerTodosLosPasos();

                audio.pausarMusicaNivel();

            } else {

                audio.reproducirMusicaNivel();
            }
        }

        if (
            pausado
        ) {

            dibujarJuego();

            menuPausa.dibujar(
                batch,
                camera
            );

            MenuPausa.Accion accion =
                menuPausa.procesarClick(
                    viewport
                );

            if (
                accion
                ==
                MenuPausa.Accion.REANUDAR
            ) {

                pausado = false;

                audio.reproducirMusicaNivel();
            }

            else if (
                accion
                ==
                MenuPausa.Accion.REINICIAR
            ) {

                reiniciarNivel();
            }

            else if (
                accion
                ==
                MenuPausa.Accion.AJUSTES
            ) {

                pausado = false;

                enAjustes = true;

                menuAjustes.abrirDesde(
                    MenuAjustes.Origen
                        .MENU_PAUSA
                );
            }

            else if (
                accion
                ==
                MenuPausa.Accion
                    .MENU_PRINCIPAL
            ) {

                pausado = false;

                menuInicio.abrir();

                audio.detenerTodosLosPasos();

                audio.detenerMusicaNivel();
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

        float delta =
            Gdx.graphics.getDeltaTime();

        mapa.actualizarMecanismos(
            azn,
            gian,
            delta
        );

        mapa.actualizarRecolectables(
            azn,
            gian,
            hud
        );

        mapa.actualizarPuertaFinal(
            hud
        );

        if (
            mapa.nivelCompletado(
                azn,
                gian,
                hud
            )
        ) {

            menuVictoria.guardarResultado(
                hud.getTiempo(),
                hud.getMonedas()
            );

            victoria = true;

            audio.detenerTodosLosPasos();

            audio.detenerMusicaNivel();

            return;
        }

        hud.actualizar();

        dibujarJuego();
    }

    private void dibujarJuego() {

        mapa.dibujar(
            camera
        );

        mapa.dibujarPuertaFinal(
            batch,
            camera
        );

        azn.dibujar(
            batch,
            camera
        );

        gian.dibujar(
            batch,
            camera
        );

        hud.dibujar(
            batch
        );
    }

    @Override
    public void resize(
        int width,
        int height
    ) {

        viewport.update(
            width,
            height,
            true
        );

        if (
            hud != null
        ) {

            hud.resize(
                width,
                height
            );
        }
    }

    @Override
    public void dispose() {

        destruirNivel();

        menuInicio.dispose();

        menuPausa.dispose();

        menuAjustes.dispose();

        menuVictoria.dispose();

        audio.dispose();

        entrada.dispose();

        batch.dispose();
    }
}