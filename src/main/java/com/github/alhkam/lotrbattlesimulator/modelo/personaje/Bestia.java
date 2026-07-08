package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

/**
 * Clase que representa a los miembros del ejército de las bestias.
 *
 * <p>Hereda de {@link Personaje} y define las reglas comunes para el bando de las bestias. Como la
 * comprobación de la validez del ataque y la mecánica de combate basada en la tirada de un único
 * dado de 100 caras.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class Bestia extends Personaje {

  /**
   * Constructor que delega la inicialización de los atributos básicos a la clase padre {@link
   * Personaje}
   *
   * @param nombre El nombre de la bestia.
   * @param puntosVida Los puntos de vida iniciales.
   * @param nivelArmadura El nivel de armadura base.
   */
  public Bestia(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  /**
   * Ejecuta la acción de atacar a un oponente.
   *
   * <p>Valida que el defensor forme parte del ejército de héroes. Si pasa la validación calcula el
   * poder de ataque, realiza el daño obtenido en la tirada de un dado de 100 caras y construye la
   * traza del ataque realizado.
   *
   * @param personaje El personaje héroe objetivo que va a recibir el ataque.
   * @return Un {@link java.lang.String} con la línea del registro del ataque.
   * @throws IllegalArgumentException Si el personaje objetivo no es una instancia de una {@link
   *     Heroe}
   */
  @Override
  public String atacar(Personaje personaje) {
    StringBuilder logAtaque = new StringBuilder();

    if (!(personaje instanceof Heroe)) {
      throw new IllegalArgumentException("Las bestias solo pueden atacar a los héroes");
    }

    int ataque = (int) (Math.random() * 100);

    int danyoRealizado = personaje.recibirDanyo(ataque, 1);

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
