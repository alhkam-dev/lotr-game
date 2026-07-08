package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

/**
 * Clase que representa a los miembros del ejército de los heroes.
 *
 * <p>Hereda de {@link Personaje} y define las reglas comunes para el ejército de héroes. Como la
 * comprobación de la validez del ataque y la mecánica de combate basada en la tirada de dos dados
 * de 100 caras, seleccionando el valor máximo obtenido.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class Heroe extends Personaje {

  /**
   * Constructor que delega la inicialización de los atributos básicos a la clase padre {@link
   * Personaje}
   *
   * @param nombre El nombre del héroe.
   * @param puntosVida Los puntos de vida iniciales.
   * @param nivelArmadura El nivel de armadura base.
   */
  public Heroe(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  /**
   * Ejecuta la acción de atacar a un oponente.
   *
   * <p>Valida que el defensor forme parte del ejército de bestias. Si pasa la validación calcula el
   * poder de ataque, realiza el daño aplicando un multiplicador de armadura neutro (1.0) y
   * construye la traza del ataque realizado.
   *
   * @param personaje El personaje bestia objetivo que va a recibir el ataque.
   * @return Un {@link java.lang.String} con la línea del registro del ataque.
   * @throws IllegalArgumentException Si el personaje objetivo no es una instancia de una {@link
   *     Bestia}
   */
  @Override
  public String atacar(Personaje personaje) {
    StringBuilder logAtaque = new StringBuilder();

    if (!(personaje instanceof Bestia)) {
      throw new IllegalArgumentException("Los héroes solo pueden atacar a las bestias");
    }

    int ataque = calcularPoderAtaque(personaje);

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

  /**
   * Calcula el poder total del ataque realizado por un héroe.
   *
   * <p>Selecciona el valor máximo obtenido en la tirada de dos dados de 100 caras mediante {@link
   * java.lang.Math#max(int, int)}
   *
   * @param personaje El personaje que va a recibir el ataque.
   * @return El valor del poder de ataque del héroe.
   */
  protected int calcularPoderAtaque(Personaje personaje) {
    int numeroDado1 = (int) (Math.random() * 100);
    int numeroDado2 = (int) (Math.random() * 100);

    return Math.max(numeroDado1, numeroDado2);
  }
}
