package com.github.alhkam.lotrbattlesimulator.modelo.heroes;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Orco;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

/**
 * Clase que representa a un Humano, un tipo de héroe.
 *
 * <p>Los humanos heredan las mecánicas de combate de la clase {@link Heroe}. Estos no cuentan con
 * bonificaciones o penalizaciones de ataque en función de su oponente.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class Humano extends Heroe {

  /**
   * Constructor que delega la inicialización de los atributos básicos a la superclase {@link Heroe}
   *
   * @param nombre El nombre del humano.
   * @param puntosVida Los puntos de vida iniciales.
   * @param nivelArmadura El nivel de armadura base.
   */
  public Humano(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }
}
