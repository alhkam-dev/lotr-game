package com.github.alhkam.lotrbattlesimulator.model.personaje;

public class Bestia extends Personaje {

    public Bestia(String nombre, int puntosVida, int nivelArmadura) {
        super(nombre, puntosVida, nivelArmadura);
    }

    @Override
    public void atacar(Personaje personaje) {
        if (!(personaje instanceof Heroe)) {
            throw new IllegalArgumentException("Beast can only attack heroes");
        }

        int numeroDado = (int) (Math.random() * 100);

        System.out.println("Die : " + numeroDado + "\nAttack power: " + numeroDado);
        personaje.recibirDanyo(numeroDado);
    }
}
