package com.github.alhkam.lotrbattlesimulator.modelo.heroes;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Trasgo;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;

public class Hobbit extends Heroe {

  public Hobbit(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  @Override
  protected int calcularPoderAtaque(Personaje personaje) {
    int poderAtaque = super.calcularPoderAtaque(personaje);

    if (personaje instanceof Trasgo) {
      poderAtaque -= 5;
    }

    return poderAtaque;
  }
}
