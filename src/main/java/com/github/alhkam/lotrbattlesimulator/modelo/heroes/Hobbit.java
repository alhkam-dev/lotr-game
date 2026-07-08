package com.github.alhkam.lotrbattlesimulator.modelo.heroes;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Orco;
import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Trasgo;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;

/**
 * Clase que representa a un Hobbit, un tipo de héroe.
 *
 * <p>Los hobbits heredan las mecánicas de combate de la clase {@link Heroe}. Estos cuentan con una
 * desventaja (-5 puntos de daño) si su oponente es un Trasgo {@link Trasgo}
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class Hobbit extends Heroe {

  /**
   * Constructor que delega la inicialización de los atributos básicos a la superclase {@link Heroe}
   *
   * @param nombre El nombre del hobbit.
   * @param puntosVida Los puntos de vida iniciales.
   * @param nivelArmadura El nivel de armadura base.
   */
  public Hobbit(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  /**
   * Calcula el poder de ataque del hobbit en su turno.
   *
   * <p>Primero calcula el poder de ataque de los héroes (el mayor valor obtenido en la tirada de
   * dos dados de 100 caras) y, a continuación aplica una reducción de poder de ataque si su
   * oponente es un {@link Trasgo}
   *
   * @param personaje El personaje que va a recibir el ataque.
   * @return El valor entero con el poder de ataque final incluyendo la penalización de daño si se cumple su
   *     condición.
   */
  @Override
  protected int calcularPoderAtaque(Personaje personaje) {
    int poderAtaque = super.calcularPoderAtaque(personaje);

    if (personaje instanceof Trasgo) {
      poderAtaque -= 5;
    }

    return poderAtaque;
  }
}
