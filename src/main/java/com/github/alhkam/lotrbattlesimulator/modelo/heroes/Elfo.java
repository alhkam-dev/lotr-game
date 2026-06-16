package com.github.alhkam.lotrbattlesimulator.modelo.heroes;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Orco;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

public class Elfo extends Heroe {

  public Elfo(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  @Override
  protected int calcularPoderAtaque(Personaje personaje) {
    int attackPower = super.calcularPoderAtaque(personaje);

    if (personaje instanceof Orco) {
      attackPower += 10;
    }

    return attackPower;
  }
}
