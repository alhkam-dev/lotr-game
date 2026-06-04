package com.github.alhkam.lotrbattlesimulator;

import com.github.alhkam.lotrbattlesimulator.model.beasts.Orco;
import com.github.alhkam.lotrbattlesimulator.model.heroes.Elfo;
import com.github.alhkam.lotrbattlesimulator.model.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.model.personaje.Heroe;

public class Main {
  public static void main(String[] args) {

    Heroe heroe = new Elfo("Aragorn", 500, 5);
    Heroe heroe2 = new Heroe("Paco", 500, 80);
    Bestia bestia = new Orco("Bla", 500, 50);
    Bestia bestia2 = new Bestia("Blop", 500, 5);

    heroe.atacar(bestia);
    bestia2.atacar(heroe2);
  }
}
