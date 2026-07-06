package com.github.alhkam.lotrbattlesimulator;

import com.github.alhkam.lotrbattlesimulator.controlador.ControladorBatalla;
import com.github.alhkam.lotrbattlesimulator.servicio.ServicioBatalla;
import com.github.alhkam.lotrbattlesimulator.vista.VistaBatalla;
import javax.swing.*;

/**
 * Clase principal que actúa como punto de entrada de la aplicación.
 *
 * <p>Se encarga de instanciar el servicio de lógica de negocio y asegurar que la interfaz gráfica
 * se inicialice de forma segura en el hilo de despacho de eventos de Swing.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
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
