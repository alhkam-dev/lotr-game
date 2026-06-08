package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultadoAtaque {

  private String atacante;
  private int tirada;
  private int danyo;
  private String defensor;

  public String toString() {
    return String.format(
        "%s saca %d y le quita %d de vida a %s",
        this.atacante, this.tirada, this.danyo, this.defensor);
  }
}
