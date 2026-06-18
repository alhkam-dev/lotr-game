package com.github.alhkam.lotrbattlesimulator;

import com.github.alhkam.lotrbattlesimulator.controlador.ControladorBatalla;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.servicio.ServicioBatalla;
import com.github.alhkam.lotrbattlesimulator.vista.VistaBatalla;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ServicioBatalla servicioBatalla = new ServicioBatalla();

    SwingUtilities.invokeLater(
        () -> {
          VistaBatalla vistaBatalla = new VistaBatalla();

          ControladorBatalla controladorBatalla =
              new ControladorBatalla(vistaBatalla, servicioBatalla);
        });
  }
}
