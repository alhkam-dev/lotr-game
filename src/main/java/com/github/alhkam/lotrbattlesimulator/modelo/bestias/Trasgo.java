package com.github.alhkam.lotrbattlesimulator.modelo.bestias;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

/**
 * Clase que representa a un Trasgo, un tipo de bestia.
 *
 * <p>Los trasgos heredan las mecánicas de combate de la clase {@link Bestia}. Estos no cuentan con
 * bonificaciones o penalizaciones de ataque en función de su oponente.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class Trasgo extends Bestia {

  /**
   * Constructor que delega la inicialización de los atributos básicos a la superclase {@link
   * Bestia}
   *
   * @param nombre El nombre del trasgo.
   * @param puntosVida Los puntos de vida iniciales.
   * @param nivelArmadura El nivel de armadura base.
   */
  public Trasgo(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }
}
