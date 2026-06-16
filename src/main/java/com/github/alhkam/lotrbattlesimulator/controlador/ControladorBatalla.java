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

public class ControladorBatalla {

  private final VistaBatalla vista;
  private final ServicioBatalla servicio;

  public ControladorBatalla(VistaBatalla vistaBatalla, ServicioBatalla servicioBatalla) {
    this.vista = vistaBatalla;
    this.servicio = servicioBatalla;

    // Enlazar eventos
    this.vista.listenerBtnAnyadirHeroe(e -> accionAnyadirHeroe());
    this.vista.listenerBtnAnyadirBestia(e -> accionAnyadirBestia());

    this.vista.listenerBtnSubirHeroe(e -> accionSubirHeroe());
    this.vista.listenerBtnBajarHeroe(e -> accionBajarHeroe());
    this.vista.listenerBtnEliminarHeroe(e -> accionEliminarHeroe());

    // 3. Enlazamos los eventos de ordenación y borrado de Bestias
    this.vista.listenerBtnSubirBestia(e -> accionSubirBestia());
    this.vista.listenerBtnBajarBestia(e -> accionBajarBestia());
    this.vista.listenerBtnEliminarBestia(e -> accionEliminarBestia());

    // 4. Enlazamos el botón del motor de juego
    this.vista.listenerBtnLuchar(e -> accionIniciarGuerra());
  }

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
          nombre
              + " - "
              + nuevoHeroe.getClass().getSimpleName()
              + "("
              + vida
              + ", "
              + armadura
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
        case "Humano":
          nuevaBestia = new Trasgo(nombre, vida, armadura);
          break;
        default:
          JOptionPane.showMessageDialog(
              vista, "Tipo de bestia no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
          return;
      }

      servicio.anyadirBestia(nuevaBestia);

      vista.anyadirBestiaLista(
          nombre
              + " - "
              + nuevaBestia.getClass().getSimpleName()
              + "("
              + vida
              + ", "
              + armadura
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

  private void accionSubirHeroe() {
    int indice = vista.getIndiceHeroeSeleccionado();
    if (indice > 0) {
      int nuevoIndice = indice - 1;
      servicio.intercambiarPosicionesHeroes(indice, nuevoIndice);
      vista.intercambiarHeroesLista(indice, nuevoIndice);
      vista.setIndiceHeroeSeleccionado(nuevoIndice);
    }
  }

  private void accionBajarHeroe() {
    int indice = vista.getIndiceHeroeSeleccionado();
    // Le preguntamos al servicio el tamaño actual de los datos para validar el límite
    if (indice != -1 && indice < servicio.getTamanyoEjercitoHeroes() - 1) {
      int nuevoIndice = indice + 1;
      servicio.intercambiarPosicionesHeroes(indice, nuevoIndice);
      vista.intercambiarHeroesLista(indice, nuevoIndice);
      vista.setIndiceHeroeSeleccionado(nuevoIndice);
    }
  }

  private void accionEliminarHeroe() {
    int indice = vista.getIndiceHeroeSeleccionado();
    if (indice != -1) {
      servicio.eliminarHeroe(indice);
      vista.eliminarHeroeLista(indice);
    }
  }
}
