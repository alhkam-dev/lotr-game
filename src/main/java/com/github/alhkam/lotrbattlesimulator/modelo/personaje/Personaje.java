package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase abstracta que define la estructura y comportamiento base de los combatientes (tanto héroes
 * como bestias) en el simulador de batallas.
 *
 * <p>Proporciona atributos comunes como nombre, vida y armadura, gestionados con Lombok, e
 * implementa la lógica genérica para la recepción de daño y comprobación de si el personaje sigue
 * vivo.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Personaje {

  /** Nombre del personaje. */
  private String nombre;

  /** Puntos de vida del personaje. Si llega a 0 se considera al personaje como muerto. */
  private int puntosVida;

  /** Nivel de armadura del personaje. */
  private int nivelArmadura;

  /**
   * Método abstracto que define la acción de atacar a otro personaje.
   *
   * <p>Cada subtipo de personaje debe implementar su propia lógica de ataque.
   *
   * @param personaje El personaje objetivo que va a recibir el ataque.
   * @return Un {@link java.lang.String} con el reporte de lo sucedido en el ataque.
   */
  public abstract String atacar(Personaje personaje);

  /**
   * Calcula el daño recibido restando la armadura al poder de ataque si el poder es mayor que la
   * armadura.
   *
   * @param poderAtaque El valor del ataque lanzado por el oponente.
   * @param armadura El valor de la armadura defensiva.
   * @return El daño resultante del cálculo realizado.
   */
  public int calcularDanyoRecibido(int poderAtaque, int armadura) {
    if (poderAtaque > armadura) {
      return poderAtaque - armadura;
    }
    return 0;
  }

  /**
   * Procesa la recepción de un ataque enemigo, aplicando modificadores al nivel de la armadura,
   * reduciendo la vida del personaje si corresponde y devolviendo el daño inflingido.
   *
   * @param poderAtaque El valor del ataque realizado por el oponente.
   * @param multiplicadorArmadura El multiplicador que altera el nivel de armadura.
   * @return El total de puntos de vida que ha perdido el personaje en el ataque.
   */
  public int recibirDanyo(int poderAtaque, double multiplicadorArmadura) {
    int armaduraEfectiva = (int) (this.nivelArmadura * multiplicadorArmadura);

    int danyo = calcularDanyoRecibido(poderAtaque, armaduraEfectiva);

    if (danyo > 0) {
      this.setPuntosVida(this.getPuntosVida() - danyo);
    }

    return danyo;
  }

  /**
   * Comprueba si el personaje a muerto en combate basándose en los puntos de vida del personaje.
   *
   * @return {@code true} soo los puntos de vida son iguales o menores a cero; {@code false} si el
   *     personaje continúa con vida.
   */
  public boolean estaMuerto() {
    return this.puntosVida <= 0;
  }
}
