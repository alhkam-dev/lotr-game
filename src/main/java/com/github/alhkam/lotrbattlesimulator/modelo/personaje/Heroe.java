package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

public class Heroe extends Personaje {

  public Heroe(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

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

  protected int calcularPoderAtaque(Personaje personaje) {
    int numeroDado1 = (int) (Math.random() * 100);
    int numeroDado2 = (int) (Math.random() * 100);

    return Math.max(numeroDado1, numeroDado2);
  }
}
