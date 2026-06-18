package com.github.alhkam.lotrbattlesimulator.modelo.bestias;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;

public class Orco extends Bestia {

  public Orco(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  @Override
  public String atacar(Personaje personaje) {
    StringBuilder logAtaque = new StringBuilder();

    if (!(personaje instanceof Heroe)) {
      throw new IllegalArgumentException("Las bestias solo pueden atacar a héroes");
    }

    int ataque = (int) (Math.random() * 100);

    int danyoRealizado = personaje.recibirDanyo(ataque, 0.9);

    logAtaque
        .append("\t")
        .append(this.getNombre())
        .append(" saca ")
        .append(ataque)
        .append(" y le quita ")
        .append(danyoRealizado)
        .append(" de vida a ")
        .append(personaje.getNombre())
        .append("\n");

    return logAtaque.toString();
  }
}
