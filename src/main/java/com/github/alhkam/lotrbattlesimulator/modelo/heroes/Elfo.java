package com.github.alhkam.lotrbattlesimulator.modelo.heroes;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Orco;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

public class Elfo extends Heroe {

    public Elfo(String name, int lifePoints, int armorLevel) {
        super(name, lifePoints, armorLevel);
    }

    @Override
    protected int calcularPoderAtaque(Personaje personaje) {
        int attackPower = super.calcularPoderAtaque(personaje);

        if (personaje instanceof Orco) {
            attackPower += 10;
            System.out.println("Elfs have +10 attack power against Orcs. New attack power: " + attackPower);
        }

        return attackPower;
    }
}
