package com.github.alhkam.lotrbattlesimulator.model.personaje;


public class Heroe extends Personaje {

    public Heroe(String nombre, int puntosVida, int nivelArmadura) {
        super(nombre, puntosVida, nivelArmadura);
    }

    @Override
    public void atacar(Personaje personaje) {

        if (!(personaje instanceof Bestia)) {
            throw new IllegalArgumentException("Hero can only attack beasts");
        }

        int poderAtaque = calcularPoderAtaque(personaje);

        personaje.recibirDanyo(poderAtaque);
    }

    protected int calcularPoderAtaque(Personaje personaje) {
        int numeroDado1 = (int) (Math.random() * 100);
        int numeroDado2 = (int) (Math.random() * 100);

        System.out.println("Die 1: " + numeroDado1);
        System.out.println("Die 2: " + numeroDado2);
        return Math.max(numeroDado1, numeroDado2);
    }
}
