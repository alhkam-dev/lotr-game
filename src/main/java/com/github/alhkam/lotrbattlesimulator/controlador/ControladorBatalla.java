package com.github.alhkam.lotrbattlesimulator.controlador;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Orco;
import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Trasgo;
import com.github.alhkam.lotrbattlesimulator.modelo.heroes.Elfo;
import com.github.alhkam.lotrbattlesimulator.modelo.heroes.Hobbit;
import com.github.alhkam.lotrbattlesimulator.modelo.heroes.Humano;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.servicio.ServicioBatalla;
import com.github.alhkam.lotrbattlesimulator.vista.VistaBatalla;

import javax.swing.*;

/**
 * Controlador principal de la aplicación en el patrón MVC.
 *
 * <p>Se encarga de actuar como intermediario entre la interfaz gráfica ({@link VistaBatalla}) y la
 * lógica de negocio ({@link ServicioBatalla}). Captura los eventos disparados por los componentes
 * de la vista, valida los datos de entrada del usuario y actualiza tanto el modelo como las listas
 * visuales de los ejércitos.
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class ControladorBatalla {

  /** Referencia a la interfaz gráfica con la que interactúa el usuario. */
  private final VistaBatalla vista;

  /** Referencia al servicio que gestiona la lógica de negocio de la simulación del combate. */
  private final ServicioBatalla servicio;

  /**
   * Constructor del controlador. Inicializa las referencias de la vista y el servicio, y enlaza las
   * acciones sobre los componentes de la vista con sus métodos con expresiones lambda.
   *
   * @param vistaBatalla Instancia de la ventana con la que interactúa el usuario.
   * @param servicioBatalla Instancia de la lógica de negocio de la batalla.
   */
  public ControladorBatalla(VistaBatalla vistaBatalla, ServicioBatalla servicioBatalla) {
    this.vista = vistaBatalla;
    this.servicio = servicioBatalla;

    // Enlazar eventos creación personajes
    this.vista.listenerBtnAnyadirHeroe(e -> accionAnyadirHeroe());
    this.vista.listenerBtnAnyadirBestia(e -> accionAnyadirBestia());

    // Enlazar eventos gestión ejercito de héroes
    this.vista.listenerBtnSubirHeroe(e -> accionSubirHeroe());
    this.vista.listenerBtnBajarHeroe(e -> accionBajarHeroe());
    this.vista.listenerBtnEliminarHeroe(e -> accionEliminarHeroe());

    // Enlazar eventos gestión ejercito de bestias
    this.vista.listenerBtnSubirBestia(e -> accionSubirBestia());
    this.vista.listenerBtnBajarBestia(e -> accionBajarBestia());
    this.vista.listenerBtnEliminarBestia(e -> accionEliminarBestia());

    // Enlzar botón de luchar
    this.vista.listenerBtnLuchar(e -> accionIniciarLucha());
  }

  // --- Creación personajes ---
  /**
   * Procesa el evento de añadir un héroe a la lista del ejército de héroes. Extrae los valores
   * introducidos en el formulario y realiza las validaciones correspondientes. Si la validación es
   * correcta instancia el tipo de héroe, actualiza el servicio e inserta la cadena con la información
   * del héroe a la lista del ejército en la interfaz.
   */
  private void accionAnyadirHeroe() {
    String nombre = vista.getNombreHeroe();
    String tipo = vista.getTipoHeroe();
    String txtVida = vista.getVidaHeroe();
    String txtArmadura = vista.getArmaduraHeroe();

    if (nombre.isBlank()) {
      JOptionPane.showMessageDialog(
          vista,
          "El nombre del héroe no puede estar vacío.",
          "Campo obligatorio",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
    if (txtVida.isBlank() || txtArmadura.isBlank()) {
      JOptionPane.showMessageDialog(
          vista,
          "La vida y la armadura son campos obligatorios.",
          "Campo obligatorio",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    try {
      int vida = Integer.parseInt(txtVida);
      int armadura = Integer.parseInt(txtArmadura);

      if (vida <= 0 || armadura < 0) {
        JOptionPane.showMessageDialog(
            vista,
            "La vida debe ser mayor que 0 y la armadura no puede ser negativa.",
            "Valores inválidos",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

      Heroe nuevoHeroe = null;
      switch (tipo) {
        case "Elfo":
          nuevoHeroe = new Elfo(nombre, vida, armadura);
          break;
        case "Humano":
          nuevoHeroe = new Humano(nombre, vida, armadura);
          break;
        case "Hobbit":
          nuevoHeroe = new Hobbit(nombre, vida, armadura);
          break;
        default:
          JOptionPane.showMessageDialog(
              vista, "Tipo de héroe no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
      }

      servicio.anyadirHeroe(nuevoHeroe);

      vista.anyadirHeroeLista(
          nuevoHeroe.getNombre()
              + " - "
              + nuevoHeroe.getClass().getSimpleName()
              + "("
              + nuevoHeroe.getPuntosVida()
              + ", "
              + nuevoHeroe.getNivelArmadura()
              + ")");
      vista.limpiarFormularios();

    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(
          vista,
          "La vida y la armadura del héroe deben ser números enteros.",
          "Error de formato",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Procesa el evento de añadir una bestia a la lista del ejército de bestias. Extrae los valores
   * introducidos en el formulario y realiza las validaciones correspondientes. Si la validación es
   * correcta instancia el tipo de bestia, actualiza el servicio e inserta la cadena con la
   * información de la bestia a la lista del ejército en la interfaz.
   */
  private void accionAnyadirBestia() {
    String nombre = vista.getNombreBestia();
    String tipo = vista.getTipoBestia();
    String txtVida = vista.getVidaBestia();
    String txtArmadura = vista.getArmaduraBestia();

    if (nombre.isBlank()) {
      JOptionPane.showMessageDialog(
          vista,
          "El nombre de la bestia no puede estar vacío.",
          "Campo obligatorio",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
    if (txtVida.isBlank() || txtArmadura.isBlank()) {
      JOptionPane.showMessageDialog(
          vista,
          "La vida y la armadura son campos obligatorios.",
          "Campo obligatorio",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    try {
      int vida = Integer.parseInt(txtVida);
      int armadura = Integer.parseInt(txtArmadura);

      if (vida <= 0 || armadura < 0) {
        JOptionPane.showMessageDialog(
            vista,
            "La vida debe ser mayor que 0 y la armadura no puede ser negativa.",
            "Valores inválidos",
            JOptionPane.WARNING_MESSAGE);
        return;
      }

      Bestia nuevaBestia = null;
      switch (tipo) {
        case "Orco":
          nuevaBestia = new Orco(nombre, vida, armadura);
          break;
        case "Trasgo":
          nuevaBestia = new Trasgo(nombre, vida, armadura);
          break;
        default:
          JOptionPane.showMessageDialog(
              vista, "Tipo de bestia no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
      }

      servicio.anyadirBestia(nuevaBestia);

      vista.anyadirBestiaLista(
          nuevaBestia.getNombre()
              + " - "
              + nuevaBestia.getClass().getSimpleName()
              + "("
              + nuevaBestia.getPuntosVida()
              + ", "
              + nuevaBestia.getNivelArmadura()
              + ")");
      vista.limpiarFormularios();

    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(
          vista,
          "La vida y la armadura de la bestia deben ser números enteros.",
          "Error de formato",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  // --- Gestión ejercitos héroes ---
  /**
   * Sube una posición al héroe seleccionado dentro de su ejército, coontrolando que no exceda el
   * límite superior del tamaño de la lista. Modifica el índice tanto en el servicio como en la
   * interfaz.
   */
  private void accionSubirHeroe() {
    int indice = vista.getIndiceHeroeSeleccionado();

    if (indice > 0) {
      int nuevoIndice = indice - 1;
      servicio.intercambiarPosicionesHeroes(indice, nuevoIndice);
      vista.intercambiarHeroesLista(indice, nuevoIndice);
      vista.setIndiceHeroeSeleccionado(nuevoIndice);
    }
  }

  /**
   * Baja una posición al héroe seleccionado dentro de su ejército, controlando que no exceda el
   * límite inferior del tamaño de la lista. Modifica el índice tanto en el servicio como en la
   * interfaz.
   */
  private void accionBajarHeroe() {
    int indice = vista.getIndiceHeroeSeleccionado();

    if (indice != -1 && indice < servicio.getTamanyoEjercitoHeroes() - 1) {
      int nuevoIndice = indice + 1;
      servicio.intercambiarPosicionesHeroes(indice, nuevoIndice);
      vista.intercambiarHeroesLista(indice, nuevoIndice);
      vista.setIndiceHeroeSeleccionado(nuevoIndice);
    }
  }

  /** Elimina del ejército al héroe seleccionado. Actualiza tanto el modelo como el servicio. */
  private void accionEliminarHeroe() {
    int indice = vista.getIndiceHeroeSeleccionado();
    if (indice != -1) {
      servicio.eliminarHeroe(indice);
      vista.eliminarHeroeLista(indice);
    }
  }

  // --- Gestión ejercitos bestias ---

  private void accionSubirBestia() {
    int indice = vista.getIndiceBestiaSeleccionado();

    if (indice > 0) {
      int nuevoIndice = indice - 1;
      servicio.intercambiarPosicionesBestias(indice, nuevoIndice);
      vista.intercambiarBestiasLista(indice, nuevoIndice);
      vista.setIndiceBestiaSeleccionado(nuevoIndice);
    }
  }

  private void accionBajarBestia() {
    int indice = vista.getIndiceBestiaSeleccionado();

    if (indice != -1 && indice < servicio.getTamanyoEjercitoBestias() - 1) {
      int nuevoIndice = indice + 1;
      servicio.intercambiarPosicionesBestias(indice, nuevoIndice);
      vista.intercambiarBestiasLista(indice, nuevoIndice);
      vista.setIndiceBestiaSeleccionado(nuevoIndice);
    }
  }

  private void accionEliminarBestia() {
    int indice = vista.getIndiceBestiaSeleccionado();
    if (indice != -1) {
      servicio.eliminarBestia(indice);
      vista.eliminarBestiaLista(indice);
    }
  }

  private void accionIniciarLucha() {
    if (!servicio.hayEjercitosListos()) {
      JOptionPane.showMessageDialog(
          vista,
          "No se puede iniciar la batalla. Se requiero al menos un personaje en cada ejercito.",
          "Ejercitos incompletos",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    vista.escribirLog("¡¡¡Comienza la Batalla por la Tierra Media!!!\n");

    String logCombate = servicio.ejecutarBatalla();
    vista.escribirLog(logCombate);
  }
}
