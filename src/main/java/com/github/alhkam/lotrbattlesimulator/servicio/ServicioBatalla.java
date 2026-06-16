package com.github.alhkam.lotrbattlesimulator.servicio;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

import java.util.ArrayList;
import java.util.List;

public class ServicioBatalla {

  private final List<Heroe> ejercitoHeroes;
  private final List<Bestia> ejercitoBestias;

  public ServicioBatalla() {
    this.ejercitoHeroes = new ArrayList<>();
    this.ejercitoBestias = new ArrayList<>();
  }

  public String ejecutarBatalla() {
    StringBuilder logBatalla = new StringBuilder();
    int turno = 1;

    while (!condicionVictoria()) {
      logBatalla.append("Turno ").append(turno).append(":\n");
      logBatalla.append(ejecutarTurno());
      turno++;
    }

    if (this.ejercitoHeroes.isEmpty()) {
      logBatalla.append("¡¡VICTORIA DE LAS BESTIAS!!");
    } else {
      logBatalla.append("¡¡VICTORIA DE LOS HÉROES!!");
    }

    return logBatalla.toString();
  }

  private String ejecutarTurno() {
    StringBuilder logCombates = new StringBuilder();

    int miembrosEjercitoMenor = Math.min(this.ejercitoHeroes.size(), this.ejercitoBestias.size());

    List<Integer> muertosHeroes = new ArrayList<>();
    List<Integer> muertosBestias = new ArrayList<>();

    // Se ejecuta turno combate
    for (int i = 0; i < miembrosEjercitoMenor; i++) {

      Heroe heroe = ejercitoHeroes.get(i);
      Bestia bestia = ejercitoBestias.get(i);

      logCombates.append(ejecutarCombate(heroe, bestia));

      if (heroe.estaMuerto()) {
        logCombates
            .append("\t¡Muere ")
            .append(heroe.getClass().getSimpleName())
            .append(" ")
            .append(heroe.getNombre())
            .append("!");
        muertosHeroes.add(i);
      }

      if (bestia.estaMuerto()) {
        logCombates
            .append("\t¡Muere ")
            .append(bestia.getClass().getSimpleName())
            .append(" ")
            .append(bestia.getNombre())
            .append("!");
        muertosBestias.add(i);
      }
    }

    // Se eliminan los combatientes fallecidos
    for (int i = muertosHeroes.size() - 1; i >= 0; i--) {
      int posicionMuerto = muertosHeroes.get(i);
      ejercitoHeroes.remove(posicionMuerto);
    }

    for (int i = muertosBestias.size() - 1; i >= 0; i--) {
      int posicionMuerto = muertosBestias.get(i);
      ejercitoBestias.remove(posicionMuerto);
    }

    return logCombates.toString();
  }

  private String ejecutarCombate(Heroe heroe, Bestia bestia) {
    StringBuilder logCombate = new StringBuilder();
    logCombate
        .append("\tLucha entre ")
        .append(heroe.getNombre())
        .append("(Vida=")
        .append(heroe.getPuntosVida())
        .append(" Armadura=")
        .append(heroe.getNivelArmadura())
        .append(") y ")
        .append(bestia.getNombre())
        .append(" (Vida=")
        .append(bestia.getPuntosVida())
        .append(" Armadura=")
        .append(bestia.getNivelArmadura())
        .append(")\n");
    logCombate.append(heroe.atacar(bestia));
    bestia.atacar(heroe);

    return logCombate.toString();
  }

  private boolean condicionVictoria() {
    return this.ejercitoHeroes.isEmpty() || this.ejercitoBestias.isEmpty();
  }

  public int getTamanyoEjercitoHeroes() {
    return this.ejercitoHeroes.size();
  }

  public int getTamanyoEjercitoBestias() {
    return this.ejercitoBestias.size();
  }

  public void intercambiarPosicionesHeroes(int indice, int nuevoIndice) {
  }
}
