package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

public class Bestia extends Personaje {

  public Bestia(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  @Override
  public String atacar(Personaje personaje) {
    StringBuilder logAtaque = new StringBuilder();

    if (!(personaje instanceof Heroe)) {
      throw new IllegalArgumentException("Las bestias solo pueden atacar a los héroes");
    }

    int ataque = (int) (Math.random() * 100);

    int danyoRealizado = personaje.recibirDanyo(ataque, 1);

    logAtaque
        .append("\t\t")
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
