package com.AiznerYGian.ScapeTechnical;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Entrada {

    private Scanner scanner;

    public Entrada() {

        scanner = new Scanner(System.in);
    }

    public boolean izquierda() {

        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public boolean derecha() {

        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public boolean salto() {

        return Gdx.input.isKeyJustPressed(Input.Keys.W);
    }

    public boolean presionoEnter() {

        return Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
    }

    public String leerTexto() {

        return scanner.nextLine();
    }

    public int leerNumero() {

        return scanner.nextInt();
    }

    public void dispose() {

        scanner.close();
    }
}