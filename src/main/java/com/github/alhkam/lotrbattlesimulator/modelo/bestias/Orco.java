package com.github.alhkam.lotrbattlesimulator.modelo.bestias;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.ResultadoAtaque;

public class Orco extends Bestia {

  public Orco(String name, int lifePoints, int armorLevel) {
    super(name, lifePoints, armorLevel);
  }

  @Override
  public ResultadoAtaque atacar(Personaje personaje) {
    if (!(personaje instanceof Heroe)) {
      throw new IllegalArgumentException("Beast can only attack heroes");
    }

    int ataque = (int) (Math.random() * 100);

    int danyoRealizado = personaje.recibirDanyo(ataque, 0.9);

    return new ResultadoAtaque(this.getNombre(), ataque, danyoRealizado, personaje.getNombre());
  }
}
