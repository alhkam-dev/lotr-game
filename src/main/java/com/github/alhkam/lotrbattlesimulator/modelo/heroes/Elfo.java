package com.github.alhkam.lotrbattlesimulator.modelo.heroes;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Orco;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

/**
 * Clase que representa a un Elfo, un tipo de héroe.
 *
 * <p>Los elfos heredan las mecánicas de combate de la clase {@link Heroe}. Estos cuentan con una
 * ventaja de bonificación de daño (10 puntos de ataque) al enfrentarse a los orcos {@link Orco}
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class Elfo extends Heroe {

  /**
   * Constructor que delega la inicialización de los atributos básicos a la superclase {@link Heroe}
   *
   * @param nombre El nombre del elfo.
   * @param puntosVida Los puntos de vida iniciales.
   * @param nivelArmadura El nivel de armadura base.
   */
  public Elfo(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  /**
   * Calcula el poder de ataque del elfo en su turno.
   *
   * <p>Primero calcula el poder de ataque de los héroes (el mayor valor obtenido en la tirada de
   * dos dados de 100 caras) y, a continuación aplica una bonificación de poder de ataque si su
   * oponente es un {@link Orco}
   *
   * @param personaje El personaje que va a recibir el ataque.
   * @return El valor entero con el poder de ataque final incluyendo el bonificador si se cumple su
   *     condición.
   */
  @Override
  protected int calcularPoderAtaque(Personaje personaje) {
    int attackPower = super.calcularPoderAtaque(personaje);

    if (personaje instanceof Orco) {
      attackPower += 10;
    }

    return attackPower;
  }
}
