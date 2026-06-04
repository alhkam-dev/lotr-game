package com.github.alhkam.lotrbattlesimulator.model.heroes;

import com.github.alhkam.lotrbattlesimulator.model.beasts.Orco;
import com.github.alhkam.lotrbattlesimulator.model.personaje.Personaje;
import com.github.alhkam.lotrbattlesimulator.model.personaje.Heroe;

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
