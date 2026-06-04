package com.github.alhkam.lotrbattlesimulator.model.heroes;

import com.github.alhkam.lotrbattlesimulator.model.beasts.Trasgo;
import com.github.alhkam.lotrbattlesimulator.model.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.model.personaje.Personaje;

public class Hobbit extends Heroe {

    public Hobbit(String nombre, int puntosVida, int nivelArmadura) {
        super(nombre, puntosVida, nivelArmadura);
    }

    @Override
    protected int calcularPoderAtaque(Personaje personaje) {
        int poderAtaque = super.calcularPoderAtaque(personaje);

        if(personaje instanceof Trasgo) {
            poderAtaque -= 5;
        }

        return poderAtaque;
    }
}
