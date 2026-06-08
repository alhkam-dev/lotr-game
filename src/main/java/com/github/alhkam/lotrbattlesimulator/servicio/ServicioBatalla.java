package com.github.alhkam.lotrbattlesimulator.servicio;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.ResultadoAtaque;

import java.util.List;

public class ServicioBatalla {

  private List<Heroe> ejercitoHeroes;
  private List<Bestia> ejercitoBestias;

  public ServicioBatalla(List<Heroe> ejercitoHeroes, List<Bestia> ejercitoBestias) {
    this.ejercitoHeroes = ejercitoHeroes;
    this.ejercitoBestias = ejercitoBestias;
  }

  public void ejecutarBatalla() {
    while (!condicionVictoria()) {}
  }

  private void ejecutarTurno(List<Heroe> ejercitoHeroes, List<Bestia> ejercitoBestias) {}

  private void ejecutarCombate(Heroe heroe, Bestia bestia) {
    System.out.printf(
        "\tLucha entre %s (Vida=%d Armadura=%d) y %s (Vida=%d Armadura=%d)\n",
        heroe.getNombre(),
        heroe.getPuntosVida(),
        heroe.getNivelArmadura(),
        bestia.getNombre(),
        bestia.getPuntosVida(),
        bestia.getNivelArmadura());
    ResultadoAtaque res1 = heroe.atacar(bestia);
    ResultadoAtaque res2 = bestia.atacar(heroe);
  }

  private boolean condicionVictoria() {
    return this.ejercitoHeroes.isEmpty() || this.ejercitoBestias.isEmpty();
  }
}
