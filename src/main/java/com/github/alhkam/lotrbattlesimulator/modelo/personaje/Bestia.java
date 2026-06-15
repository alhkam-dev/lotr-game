package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

public class Bestia extends Personaje {

  public Bestia(String nombre, int puntosVida, int nivelArmadura) {
    super(nombre, puntosVida, nivelArmadura);
  }

  @Override
  public void atacar(Personaje personaje) {
    if (!(personaje instanceof Heroe)) {
      throw new IllegalArgumentException("Las bestias solo pueden atacar a los héroes");
    }

    int ataque = (int) (Math.random() * 100);

    int danyoRealizado = personaje.recibirDanyo(ataque, 1);

    System.out.printf("\t\t%s saca %d y le quita %d de vida a %s\n",
            this.getNombre(), ataque, danyoRealizado, personaje.getNombre());
  }
}
