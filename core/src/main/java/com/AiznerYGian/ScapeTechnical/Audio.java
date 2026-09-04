package com.AiznerYGian.ScapeTechnical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;

public class Audio {

    private Music musicaNivel;

    private Sound sonidoSalto;
    private Sound sonidoPasos;

    private float volumenEfectos;
    private float volumenMusica;

    private long pasosAznId;
    private long pasosGianId;

    private boolean pasosAznActivos;
    private boolean pasosGianActivos;

    public Audio() {

        musicaNivel = Gdx.audio.newMusic(
            Gdx.files.internal(
                "Audios/musica_nivel.mp3"
            )
        );

        sonidoSalto = Gdx.audio.newSound(
            Gdx.files.internal(
                "Audios/salto.mp3"
            )
        );

        sonidoPasos = Gdx.audio.newSound(
            Gdx.files.internal(
                "Audios/pasos.mp3"
            )
        );

        volumenEfectos = 0.70f;
        volumenMusica = 0.50f;

        musicaNivel.setLooping(true);
        musicaNivel.setVolume(volumenMusica);

        pasosAznActivos = false;
        pasosGianActivos = false;
    }

    public void reproducirMusicaNivel() {

        if (!musicaNivel.isPlaying()) {

            musicaNivel.play();
        }
    }

    public void detenerMusicaNivel() {

        musicaNivel.stop();
    }

    public void pausarMusicaNivel() {

        if (musicaNivel.isPlaying()) {

            musicaNivel.pause();
        }
    }

    public void reproducirSalto() {

        sonidoSalto.play(
            volumenEfectos
        );
    }

    public void iniciarPasos(
        int jugador
    ) {

        if (jugador == 1) {

            if (!pasosAznActivos) {

                pasosAznId = sonidoPasos.loop(
                    volumenEfectos
                );

                pasosAznActivos = true;
            }

        } else if (jugador == 2) {

            if (!pasosGianActivos) {

                pasosGianId = sonidoPasos.loop(
                    volumenEfectos
                );

                pasosGianActivos = true;
            }
        }
    }

    public void detenerPasos(
        int jugador
    ) {

        if (jugador == 1) {

            if (pasosAznActivos) {

                sonidoPasos.stop(
                    pasosAznId
                );

                pasosAznActivos = false;
            }

        } else if (jugador == 2) {

            if (pasosGianActivos) {

                sonidoPasos.stop(
                    pasosGianId
                );

                pasosGianActivos = false;
            }
        }
    }

    public void detenerTodosLosPasos() {

        detenerPasos(1);
        detenerPasos(2);
    }

    public void setVolumenEfectos(
        float volumen
    ) {

        volumenEfectos =
            MathUtils.clamp(
                volumen,
                0f,
                1f
            );

        if (pasosAznActivos) {

            sonidoPasos.setVolume(
                pasosAznId,
                volumenEfectos
            );
        }

        if (pasosGianActivos) {

            sonidoPasos.setVolume(
                pasosGianId,
                volumenEfectos
            );
        }
    }

    public void setVolumenMusica(
        float volumen
    ) {

        volumenMusica =
            MathUtils.clamp(
                volumen,
                0f,
                1f
            );

        musicaNivel.setVolume(
            volumenMusica
        );
    }

    public float getVolumenEfectos() {

        return volumenEfectos;
    }

    public float getVolumenMusica() {

        return volumenMusica;
    }

    public void dispose() {

        musicaNivel.dispose();
        sonidoSalto.dispose();
        sonidoPasos.dispose();
    }
}