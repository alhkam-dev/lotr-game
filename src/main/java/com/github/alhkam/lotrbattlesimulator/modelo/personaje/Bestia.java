package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

public class Bestia extends Personaje {

  public Bestia(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  @Override
  public ResultadoAtaque atacar(Personaje personaje) {
    if (!(personaje instanceof Heroe)) {
      throw new IllegalArgumentException("Beast can only attack heroes");
    }

    int numeroDado = (int) (Math.random() * 100);

    int danyoRealizado = personaje.recibirDanyo(numeroDado, 1);

    return new ResultadoAtaque(this.getNombre(), numeroDado, danyoRealizado, personaje.getNombre());
  }
}
