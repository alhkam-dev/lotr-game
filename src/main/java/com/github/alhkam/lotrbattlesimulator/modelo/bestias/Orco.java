package com.github.alhkam.lotrbattlesimulator.modelo.bestias;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Personaje;

/**
 * Clase que representa a un Orco, un tipo de bestia.
 *
 * <p>Los orcos heredan las mecánicas de combate de la clase {@link Bestia}. Estos cuentan con una
 * ventaja de bonificación al enfrentarse a los héroes {@link Heroe} reduciendo su armadura efectiva
 * al 90%.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class Orco extends Bestia {

  /**
   * Constructor que delega la inicialización de los atributos básicos a la superclase {@link
   * Bestia}
   *
   * @param nombre El nombre del orco.
   * @param puntosVida Los puntos de vida iniciales.
   * @param nivelArmadura El nivel de armadura base.
   */
  public Orco(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  /**
   * Calcula el poder de ataque del orco en su turno.
   *
   * <p>Primero calcula el poder de ataque de las bestias (el valor obtenido en la tirada de un dado
   * de 100 caras) y, a continuación aplica una reducción de armadura del héroe al 90% de su
   * efectividad.
   *
   * @param personaje El personaje que va a recibir el ataque.
   * @return Un {@link java.lang.String} con el registro del ataque realizado por el orco.
   * @throws IllegalArgumentException Si el personaje objetivo no pertenece al bando de los héroes.
   */
  @Override
  public String atacar(Personaje personaje) {
    StringBuilder logAtaque = new StringBuilder();

    if (!(personaje instanceof Heroe)) {
      throw new IllegalArgumentException("Las bestias solo pueden atacar a héroes");
    }

    int ataque = (int) (Math.random() * 100);

    int danyoRealizado = personaje.recibirDanyo(ataque, 0.9);

    logAtaque
        .append("\t")
        .append(this.getNombre())
        .append(" saca ")
        .append(ataque)
        .append(" y le quita ")
        .append(danyoRealizado)
        .append(" de vida a ")
        .append(personaje.getNombre())
        .append("\n");

    return logAtaque.toString();
  }
}
