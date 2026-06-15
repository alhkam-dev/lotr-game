package com.github.alhkam.lotrbattlesimulator.modelo.personaje;


public class Heroe extends Personaje {

    public Heroe(String nombre, int puntosVida, int nivelArmadura) {
        super(nombre, puntosVida, nivelArmadura);
    }

    @Override
    public void atacar(Personaje personaje) {

        if (!(personaje instanceof Bestia)) {
            throw new IllegalArgumentException("Los héroes solo pueden atacar a las bestias");
        }

        int ataque = calcularPoderAtaque(personaje);

        int danyoRealizado = personaje.recibirDanyo(ataque, 1);

        System.out.printf("\t\t%s saca %d y le quita %d de vida a %s\n",
                this.getNombre(), ataque, danyoRealizado, personaje.getNombre());
    }

    protected int calcularPoderAtaque(Personaje personaje) {
        int numeroDado1 = (int) (Math.random() * 100);
        int numeroDado2 = (int) (Math.random() * 100);

        return Math.max(numeroDado1, numeroDado2);
    }
}
