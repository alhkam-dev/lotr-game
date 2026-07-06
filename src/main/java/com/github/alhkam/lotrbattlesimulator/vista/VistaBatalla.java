package com.github.alhkam.lotrbattlesimulator.vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * Vista de la aplicación 'lotrbattlesimulator'. Diseñada utilizando Swing
 *
 * @author Sergio Aparicio Ramírez
 * @version 1.0
 */
public class VistaBatalla extends JFrame {

  /** Campos de texto y selección para la creación de héroes. */
  private JTextField txtNombreHeroe;

  private JComboBox<String> cbTipoHeroe;
  private JTextField txtVidaHeroe;
  private JTextField txtArmaduraHeroe;
  private JButton btnAnyadirHeroe;

  /** Campos de texto y selección para la creación de bestias. */
  private JTextField txtNombreBestia;

  private JComboBox<String> cbTipoBestia;
  private JTextField txtVidaBestia;
  private JTextField txtArmaduraBestia;
  private JButton btnAnyadirBestia;

  /** Lista y modelo de datos para el ejército de héroes. Así como sus botones de gestión. */
  private final DefaultListModel<String> modeloListaHeroes = new DefaultListModel<>();

  private JList<String> listaHeroes;
  private JButton btnSubirHeroe, btnBajarHeroe, btnEliminarHeroe;

  /** Lista y modelo de datos para el ejército de bestias. Así como sus botones de gestión. */
  private final DefaultListModel<String> modeloListaBestias = new DefaultListModel<>();

  private JList<String> listaBestias;
  private JButton btnSubirBestia, btnBajarBestia, btnEliminarBestia;

  /**
   * Componentes dedicados a la batalla. Su botón de inicio y texto con scroll que muestra la
   * simulación de esta.
   */
  private JButton btnLucha;

  private JTextArea txtLogBatalla;

  /**
   * Constructor de la vista. Empleado para gestionar las dimensiones de la ventana y
   * comportamientos. Y crea la jerarquía de paneles internos de la interfaz
   */
  public VistaBatalla() {
    setTitle("Batalla por la Tierra Media");
    setSize(600, 700);
    setMinimumSize(new Dimension(600, 700));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setLayout(new BorderLayout(10, 10));
    ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

    // Panel superior
    JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));

    panelSuperior.add(panelCrearHeroes());
    panelSuperior.add(panelCrearBestias());

    add(panelSuperior, BorderLayout.NORTH);

    // Panel central
    JPanel panelCentro = new JPanel(new BorderLayout(5, 5));
    panelCentro.setBorder(BorderFactory.createTitledBorder("Lucha"));

    JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));
    panelListas.add(panelListaEjercito("Héroes"));
    panelListas.add(panelListaEjercito("Bestias"));

    // Panel contenedor para listas, botón luchar y logs
    JPanel panelContenidoLucha = new JPanel();
    panelContenidoLucha.setLayout(new BoxLayout(panelContenidoLucha, BoxLayout.Y_AXIS));

    // Botón lucha
    btnLucha = new JButton("¡LUCHA!");
    btnLucha.setPreferredSize(new Dimension(100, 30));
    btnLucha.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Texto batalla
    txtLogBatalla = new JTextArea(5, 20);
    txtLogBatalla.setEditable(false);
    JScrollPane scrollLog = new JScrollPane(txtLogBatalla);
    scrollLog.setAlignmentX(Component.CENTER_ALIGNMENT);

    panelContenidoLucha.add(btnLucha);
    panelContenidoLucha.add(Box.createRigidArea(new Dimension(0, 10)));
    panelContenidoLucha.add(scrollLog);

    panelCentro.add(panelListas, BorderLayout.NORTH);
    panelCentro.add(panelContenidoLucha, BorderLayout.CENTER);

    add(panelCentro, BorderLayout.CENTER);

    setVisible(true);
  }

  /**
   * Construye el formulario de creación de héroes.
   *
   * @return Un {@link javax.swing.JPanel} con los componentes de creación de héroes.
   */
  private JPanel panelCrearHeroes() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createTitledBorder("Héroes"));

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5, 5, 5, 5);
    c.fill = GridBagConstraints.HORIZONTAL;

    // Nombre
    c.gridx = 0;
    c.gridy = 0;
    panel.add(new JLabel("Nombre:"), c);
    c.gridx = 1;
    txtNombreHeroe = new JTextField(10);
    panel.add(txtNombreHeroe, c);

    // Tipo
    c.gridx = 0;
    c.gridy = 1;
    panel.add(new JLabel("Tipo:"), c);
    c.gridx = 1;
    cbTipoHeroe = new JComboBox<>(new String[] {"Elfo", "Humano", "Hobbit"});
    panel.add(cbTipoHeroe, c);

    // Vida
    c.gridx = 0;
    c.gridy = 2;
    panel.add(new JLabel("Vida:"), c);
    c.gridx = 1;
    txtVidaHeroe = new JTextField(5);
    panel.add(txtVidaHeroe, c);

    // Armadura
    c.gridx = 0;
    c.gridy = 3;
    panel.add(new JLabel("Armadura:"), c);
    c.gridx = 1;
    txtArmaduraHeroe = new JTextField(5);
    panel.add(txtArmaduraHeroe, c);

    // Botón
    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth = 2;

    btnAnyadirHeroe = new JButton("Añadir");
    btnAnyadirHeroe.setPreferredSize(new Dimension(80, 25));

    JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelBoton.add(btnAnyadirHeroe);

    panel.add(panelBoton, c);

    return panel;
  }

  /**
   * Construye el formulario de creación de bestias.
   *
   * @return Un {@link javax.swing.JPanel} con los componentes de creación de bestias.
   */
  private JPanel panelCrearBestias() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createTitledBorder("Bestias"));

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5, 5, 5, 5);
    c.fill = GridBagConstraints.HORIZONTAL;

    // Nombre
    c.gridx = 0;
    c.gridy = 0;
    panel.add(new JLabel("Nombre:"), c);
    c.gridx = 1;
    txtNombreBestia = new JTextField(10);
    panel.add(txtNombreBestia, c);

    // Tipo
    c.gridx = 0;
    c.gridy = 1;
    panel.add(new JLabel("Tipo:"), c);
    c.gridx = 1;
    cbTipoBestia = new JComboBox<>(new String[] {"Orco", "Trasgo"});
    panel.add(cbTipoBestia, c);

    // Vida
    c.gridx = 0;
    c.gridy = 2;
    panel.add(new JLabel("Vida:"), c);
    c.gridx = 1;
    txtVidaBestia = new JTextField(5);
    panel.add(txtVidaBestia, c);

    // Armadura
    c.gridx = 0;
    c.gridy = 3;
    panel.add(new JLabel("Armadura:"), c);
    c.gridx = 1;
    txtArmaduraBestia = new JTextField(5);
    panel.add(txtArmaduraBestia, c);

    // Botón
    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth = 2;

    btnAnyadirBestia = new JButton("Añadir");
    btnAnyadirBestia.setPreferredSize(new Dimension(80, 25));

    JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelBoton.add(btnAnyadirBestia);

    panel.add(panelBoton, c);

    return panel;
  }

  /**
   * Crea un panel que contiene la lista de personajes de un ejército y sus botones de acción
   * (Subir, Bajar y Eliminar).
   *
   * @param titulo El nombre del ejército que tiene el panel (Actualmente se utiliza "Héroes" o
   *     "Bestias" para la simulación)
   * @return Un {@link javax.swing.JPanel} con la lista y sus botones de gestión de ejércitos.
   */
  private JPanel panelListaEjercito(String titulo) {
    JPanel panel = new JPanel(new BorderLayout());
    Border bordeSinLinea = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    TitledBorder borderConTitulo = BorderFactory.createTitledBorder(bordeSinLinea, titulo);
    panel.setBorder(borderConTitulo);

    JList<String> lista;
    JButton btnSubir = new JButton("Subir");
    JButton btnBajar = new JButton("Bajar");
    JButton btnEliminar = new JButton("Eliminar");

    if (titulo.equals("Héroes")) {
      listaHeroes = new JList<>(modeloListaHeroes);
      lista = listaHeroes;

      btnSubirHeroe = btnSubir;
      btnBajarHeroe = btnBajar;
      btnEliminarHeroe = btnEliminar;
    } else {
      listaBestias = new JList<>(modeloListaBestias);
      lista = listaBestias;

      btnSubirBestia = btnSubir;
      btnBajarBestia = btnBajar;
      btnEliminarBestia = btnEliminar;
    }

    JScrollPane scroll = new JScrollPane(lista);
    scroll.setPreferredSize(new Dimension(200, 150));
    panel.add(scroll, BorderLayout.CENTER);

    JPanel botones = new JPanel();
    botones.add(btnSubir);
    botones.add(btnBajar);
    botones.add(btnEliminar);

    panel.add(botones, BorderLayout.SOUTH);

    return panel;
  }

  // Suscripción a listeners

  /**
   * Registra un Listener para el botón Añadir héroe.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del botón.
   */
  public void listenerBtnAnyadirHeroe(ActionListener listener) {
    btnAnyadirHeroe.addActionListener(listener);
  }

  /**
   * Registra un Listener para el botón Añadir bestia.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del botón.
   */
  public void listenerBtnAnyadirBestia(ActionListener listener) {
    btnAnyadirBestia.addActionListener(listener);
  }

  /**
   * Registra un Listener para iniciar la simulación de la batalla.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de dar inicio a la lógica del combate.
   */
  public void listenerBtnLuchar(ActionListener listener) {
    btnLucha.addActionListener(listener);
  }

  /**
   * Registra un Listener para el botón Subir héroe.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del ratón.
   */
  public void listenerBtnSubirHeroe(ActionListener listener) {
    btnSubirHeroe.addActionListener(listener);
  }

  /**
   * Registra un Listener para el botón Bajar héroe.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del ratón.
   */
  public void listenerBtnBajarHeroe(ActionListener listener) {
    btnBajarHeroe.addActionListener(listener);
  }

  /**
   * Registra un Listener para el botón Eliminar héroe.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del ratón.
   */
  public void listenerBtnEliminarHeroe(ActionListener listener) {
    btnEliminarHeroe.addActionListener(listener);
  }

  /**
   * Registra un Listener para el botón Subir bestia.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del ratón.
   */
  public void listenerBtnSubirBestia(ActionListener listener) {
    btnSubirBestia.addActionListener(listener);
  }

  /**
   * Registra un Listener para el botón Bajar bestia.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del ratón.
   */
  public void listenerBtnBajarBestia(ActionListener listener) {
    btnBajarBestia.addActionListener(listener);
  }

  /**
   * Registra un Listener para el botón Eliminar bestia.
   * Permite vincular la acción de la vista con el controlador.
   * @param listener El objeto encargado de procesar el clic del ratón.
   */
  public void listenerBtnEliminarBestia(ActionListener listener) {
    btnEliminarBestia.addActionListener(listener);
  }

  // Getters campos
  /**
   * Recupera el texto introducido en el campo del nombre del héroe.
   * @return Cadena de texto con el nombre del héroe.
   */
  public String getNombreHeroe() {
    return txtNombreHeroe.getText();
  }

  /**
   * Recupera el tipo seleccionado en el campo del tipo de héroe.
   * @return Cadena de texto con el tipo de héroe seleccionado.
   */
  public String getTipoHeroe() {
    return (String) cbTipoHeroe.getSelectedItem();
  }

  /**
   * Recupera el texto introducido en el campo de la vida del héroe.
   * @return Cadena de texto con la vida del héroe.
   */
  public String getVidaHeroe() {
    return txtVidaHeroe.getText();
  }

  /**
   * Recupera el texto introducido en el campo de la armadura del héroe.
   * @return Cadena de texto con la armadura del héroe.
   */
  public String getArmaduraHeroe() {
    return txtArmaduraHeroe.getText();
  }

  /**
   * Recupera el texto introducido en el campo del nombre de la bestia.
   * @return Cadena de texto con el nombre de la bestia.
   */
  public String getNombreBestia() {
    return txtNombreBestia.getText();
  }

  /**
   * Recupera el tipo seleccionado en el campo del tipo de bestia.
   * @return Cadena de texto con el tipo de bestia seleccionada.
   */
  public String getTipoBestia() {
    return (String) cbTipoBestia.getSelectedItem();
  }

  /**
   * Recupera el texto introducido en el campo de la vida de la bestia.
   * @return Cadena de texto con la vida de la bestia.
   */
  public String getVidaBestia() {
    return txtVidaBestia.getText();
  }

  /**
   * Recupera el texto introducido en el campo de la armadura de la bestia.
   * @return Cadena de texto con la vida de la armadura de la bestia.
   */
  public String getArmaduraBestia() {
    return txtArmaduraBestia.getText();
  }

  // Interacción con lista
  /**
   * Recupera el indice del héroe seleccionado en la lista de héroes.
   * @return Un entero con la posición del héroe seleccionado en la lista de héroes.
   */
  public int getIndiceHeroeSeleccionado() {
    return listaHeroes.getSelectedIndex();
  }

  /**
   * Recupera el indice de la bestia seleccionada en la lista de bestias.
   * @return Un entero con la posición de la bestia seleccionada en la lista de bestias.
   */
  public int getIndiceBestiaSeleccionado() {
    return listaBestias.getSelectedIndex();
  }

  /**
   * Modifica el indice del héroe seleccionado en la lista.
   * @param index El nuevo índice base cero del héroe seleccionado.
   */
  public void setIndiceHeroeSeleccionado(int index) {
    listaHeroes.setSelectedIndex(index);
  }

  /**
   * Modifica el indice de la bestia seleccionada en la lista.
   * @param index El nuevo índice base cero de la bestia seleccionada.
   */
  public void setIndiceBestiaSeleccionado(int index) {
    listaBestias.setSelectedIndex(index);
  }

  // Actualizar lista
  /**
   * Añade un nuevo héroe al final del modelo de la lista de héroes.
   * @param texto Cadena con los datos del héroe a listar.
   */
  public void anyadirHeroeLista(String texto) {
    modeloListaHeroes.addElement(texto);
  }

  /**
   * Añade una nueva bestia al final del modelo de la lista de bestias.
   * @param texto Cadena con los datos de la bestia a listar.
   */
  public void anyadirBestiaLista(String texto) {
    modeloListaBestias.addElement(texto);
  }

  /**
   * Elimina el héroe situado en la posición indicada del modelo de la lista.
   * @param index Índice base cero del elemento de la lista de héroes que se va a eliminar.
   */
  public void eliminarHeroeLista(int index) {
    modeloListaHeroes.remove(index);
  }

  /**
   * Elimina la bestia situada en la posición indicada del modelo de la lista.
   * @param index Índice base cero del elemento de la lista de bestias que se va a eliminar.
   */
  public void eliminarBestiaLista(int index) {
    modeloListaBestias.remove(index);
  }

  // Métodos que controlan elementos de la vista
  /**
   * Intercambia las posiciones de dos héroes dentro del modelo de la lista de héroes.
   * @param i Índice del primer elemento.
   * @param j Índice del segundo elemento.
   */
  public void intercambiarHeroesLista(int i, int j) {
    String temp = modeloListaHeroes.get(i);
    modeloListaHeroes.set(i, modeloListaHeroes.get(j));
    modeloListaHeroes.set(j, temp);
  }

  /**
   * Intercambia las posiciones de dos bestias dentro del modelo de la lista de bestias.
   * @param i Índice del primer elemento.
   * @param j Índice del segundo elemento.
   */
  public void intercambiarBestiasLista(int i, int j) {
    String temp = modeloListaBestias.get(i);
    modeloListaBestias.set(i, modeloListaBestias.get(j));
    modeloListaBestias.set(j, temp);
  }

  /**
   * Deja todos los textos de creación de héroes y bestias en blanco.
   */
  public void limpiarFormularios() {
    txtNombreHeroe.setText("");
    txtVidaHeroe.setText("");
    txtArmaduraHeroe.setText("");
    txtNombreBestia.setText("");
    txtVidaBestia.setText("");
    txtArmaduraBestia.setText("");
  }

  /**
   * Añade una nueva línea de texto al registro de la batalla.
   * @param texto Mensaje que se desea introducir en el registro de batalla.
   */
  public void escribirLog(String texto) {
    txtLogBatalla.append(texto + "\n");
  }
}
