package com.github.alhkam.lotrbattlesimulator.modelo.bestias;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;

public class Orco extends Bestia {

    public Orco(String name, int lifePoints, int armorLevel) {
        super(name, lifePoints, armorLevel);
    }

    @Override
    public void atacar(Personaje personaje) {
        if (!(personaje instanceof Heroe)) {
            throw new IllegalArgumentException("Las bestias solo pueden atacar a héroes");
        }

        int ataque = (int) (Math.random() * 100);

        int danyoRealizado = personaje.recibirDanyo(ataque, 0.9);

        System.out.printf("\t\t%s saca %d y le quita %d de vida a %s\n",
                this.getNombre(), ataque, danyoRealizado, personaje.getNombre());
    }
}
