package com.github.alhkam.lotrbattlesimulator.servicio;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Servicio de lógica de negocio encargado de gestionar el simulador.
 *
 * <p>Mantiene las colecciones de los ejércitos en memoria y controla el ciclo de vida de la
 * batalla, incluyendo el añadir, eliminar y ordenar personajes en los ejércitos, y la ejecución del
 * combate entre los distintos ejércitos.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class ServicioBatalla {

  /** Lista que almacena los héroes activos en el ejército */
  private final List<Heroe> ejercitoHeroes;

  /** Lista que almacena las bestias activas en el ejército */
  private final List<Bestia> ejercitoBestias;

  /** Constructor del servicio. Inicializa las listas de los ejércitos como colecciones vacías. */
  public ServicioBatalla() {
    this.ejercitoHeroes = new ArrayList<>();
    this.ejercitoBestias = new ArrayList<>();
  }

  // --- Métodos de añadir personajes a ejercitos ---
  /**
   * Registra un nuevo héroe en el ejército de héroes si su instancia no es nula.
   *
   * @param heroe Instancia del héroe a añadir.
   */
  public void anyadirHeroe(Heroe heroe) {
    if (heroe != null) {
      ejercitoHeroes.add(heroe);
    }
  }

  /**
   * Registra una nueva bestia en el ejército de bestias si su instancia no es nula.
   *
   * @param bestia Instancia de la bestia a añadir.
   */
  public void anyadirBestia(Bestia bestia) {
    if (bestia != null) {
      ejercitoBestias.add(bestia);
    }
  }

  // --- Métodos de eliminar personajes de ejercitos ---
  /**
   * Elimina un héroe del ejercito de héroes basándose en su posición dentro de la lista. Antes
   * valida que el índice se encuentre dentro de los límites de la lista.
   *
   * @param indice Posición base cero del héroe a eliminar
   */
  public void eliminarHeroe(int indice) {
    if (indice >= 0 && indice < ejercitoHeroes.size()) {
      ejercitoHeroes.remove(indice);
    }
  }

  /**
   * Elimina una bestia del ejercito de bestias basándose en su posición dentro de la lista. Antes
   * valida que el índice se encuentre dentro de los límites de la lista.
   *
   * @param indice Posición base cero de la bestia a eliminar
   */
  public void eliminarBestia(int indice) {
    if (indice >= 0 && indice < ejercitoBestias.size()) {
      ejercitoBestias.remove(indice);
    }
  }

  // --- Métodos para intercambiar posiciones en ejercitos ---
  /**
   * Intercambia las posiciones de dos héroes dentro de la lista para modificar la prioridad en el
   * combate.
   *
   * @param indice Posición base cero actual del héroe.
   * @param nuevoIndice Nueva posición base cero del héroe dentro del ejército.
   */
  public void intercambiarPosicionesHeroes(int indice, int nuevoIndice) {
    if (indice >= 0
        && indice < ejercitoHeroes.size()
        && nuevoIndice >= 0
        && nuevoIndice < ejercitoHeroes.size()) {
      Collections.swap(ejercitoHeroes, indice, nuevoIndice);
    }
  }

  /**
   * Intercambia las posiciones de dos bestias dentro de la lista para modificar la prioridad en el
   * combate.
   *
   * @param indice Posición base cero actual de la bestia.
   * @param nuevoIndice Nueva posición base cero de la bestia dentro del ejército.
   */
  public void intercambiarPosicionesBestias(int indice, int nuevoIndice) {
    if (indice >= 0
        && indice < ejercitoBestias.size()
        && nuevoIndice >= 0
        && nuevoIndice < ejercitoBestias.size()) {
      Collections.swap(ejercitoBestias, indice, nuevoIndice);
    }
  }

  // --- Métodos para validaciones ---
  /**
   * Obtiene la cantidad de héroes con vida dentro del ejército.
   *
   * @return Número total de héroes en el ejército
   */
  public int getTamanyoEjercitoHeroes() {
    return this.ejercitoHeroes.size();
  }

  /**
   * Obtiene la cantidad de bestias con vida dentro del ejército.
   *
   * @return Número total de bestais en el ejército
   */
  public int getTamanyoEjercitoBestias() {
    return this.ejercitoBestias.size();
  }

  /**
   * Comprueba si ambos ejércitos cuentan con al menos un integrante para poder iniciar la batalla.
   *
   * @return {@code true} si ambas listas no están vacías; {@code false} En caso contrario.
   */
  public boolean hayEjercitosListos() {
    return !this.ejercitoHeroes.isEmpty() && !this.ejercitoBestias.isEmpty();
  }

  // --- Lógica de la batalla ---

  /**
   * Ejecuta la lógica de la batalla hasta que uno de los dos ejercitos se quede sin combatientes.
   *
   * @return String con la información de la batalla
   */
  public String ejecutarBatalla() {
    StringBuilder logBatalla = new StringBuilder();
    int turno = 1;

    while (!condicionVictoria()) {
      logBatalla.append("Turno ").append(turno).append(":\n");
      logBatalla.append(ejecutarTurno());
      turno++;
    }

    if (this.ejercitoHeroes.isEmpty() && this.ejercitoBestias.isEmpty()) {
      logBatalla.append("\n¡¡ AMBOS EJERCITOS CAEN DERROTADOS !!");
    } else if (this.ejercitoHeroes.isEmpty()) {
      logBatalla.append("\n¡¡VICTORIA DE LAS BESTIAS!!");
    } else {
      logBatalla.append("\n¡¡VICTORIA DE LOS HÉROES!!");
    }

    return logBatalla.toString();
  }

  /**
   * Ejecuta todos los combates que tienen lugar en un turno. Este tiene en cuenta al ejército con
   * el menor número de combatientes ya que los combates son 1vs1. Registra las muertes de los
   * combatientes y la gestión de los combates que tendrán lugar.
   *
   * @return String con los combatientes que caen en batalla
   */
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
            .append("    * ¡Muere ")
            .append(heroe.getClass().getSimpleName())
            .append(" ")
            .append(heroe.getNombre())
            .append("!\n");
        muertosHeroes.add(i);
      }

      if (bestia.estaMuerto()) {
        logCombates
            .append("    * ¡Muere ")
            .append(bestia.getClass().getSimpleName())
            .append(" ")
            .append(bestia.getNombre())
            .append("!\n");
        muertosBestias.add(i);
      }
    }

    // Se eliminan los combatientes fallecidos (en sentido inverso para no alterar los indices al
    // eliminar combatientes de los ejércitos)
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

  /**
   * Enfrenta a un héroe contra una bestia
   *
   * @param heroe El héore atacante/defensor.
   * @param bestia La bestia atacante/defensor.
   * @return String con la información de los combatientes en batalla y la información de su
   *     combate.
   */
  private String ejecutarCombate(Heroe heroe, Bestia bestia) {
    StringBuilder logCombate = new StringBuilder();
    logCombate
        .append("    · Lucha entre ")
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
    logCombate.append(bestia.atacar(heroe));

    return logCombate.toString();
  }

  /**
   * Evalúa la condición de finalización de la simulación.
   *
   * @return {@code true} si al menos uno de los dos ejércitos se ha quedado sin combatientes.
   */
  private boolean condicionVictoria() {
    return this.ejercitoHeroes.isEmpty() || this.ejercitoBestias.isEmpty();
  }
}
