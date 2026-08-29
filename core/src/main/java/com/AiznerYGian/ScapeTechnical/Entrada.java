package com.AiznerYGian.ScapeTechnical;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;

public class Entrada {

    private Scanner scanner;

    public Entrada() {
        scanner = new Scanner(System.in);
    }

    public boolean teclaPresionada(int tecla) {
        return Gdx.input.isKeyPressed(tecla);
    }

    public boolean teclaJustoPresionada(int tecla) {
        return Gdx.input.isKeyJustPressed(tecla);
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