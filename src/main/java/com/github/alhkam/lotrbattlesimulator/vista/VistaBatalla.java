package com.github.alhkam.lotrbattlesimulator.vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class VistaBatalla extends JFrame {

  // Campos creación héroe
  private JTextField txtNombreHeroe;
  private JComboBox<String> cbTipoHeroe;
  private JTextField txtVidaHeroe;
  private JTextField txtArmaduraHeroe;
  private JButton btnAnyadirHeroe;

  // Campos creación bestia
  private JTextField txtNombreBestia;
  private JComboBox<String> cbTipoBestia;
  private JTextField txtVidaBestia;
  private JTextField txtArmaduraBestia;
  private JButton btnAnyadirBestia;

  // Lista heroes y componentes
  private final DefaultListModel<String> modeloListaHeroes = new DefaultListModel<>();
  private JList<String> listaHeroes;
  private JButton btnSubirHeroe, btnBajarHeroe, btnEliminarHeroe;

  // Lista heroes y componentes
  private final DefaultListModel<String> modeloListaBestias = new DefaultListModel<>();
  private JList<String> listaBestias;
  private JButton btnSubirBestia, btnBajarBestia, btnEliminarBestia;

  // Batalla
  private JButton btnLucha;
  private JTextArea txtLogBatalla;

  public VistaBatalla() {
    setTitle("Batalla por la Tierra Media");
    setSize(600, 700);
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
  public void listenerBtnAnyadirHeroe(ActionListener listener) {
    btnAnyadirHeroe.addActionListener(listener);
  }

  public void listenerBtnAnyadirBestia(ActionListener listener) {
    btnAnyadirBestia.addActionListener(listener);
  }

  public void listenerBtnLuchar(ActionListener listener) {
    btnLucha.addActionListener(listener);
  }

  public void listenerBtnSubirHeroe(ActionListener listener) {
    btnSubirHeroe.addActionListener(listener);
  }

  public void listenerBtnBajarHeroe(ActionListener listener) {
    btnBajarHeroe.addActionListener(listener);
  }

  public void listenerBtnEliminarHeroe(ActionListener listener) {
    btnEliminarHeroe.addActionListener(listener);
  }

  public void listenerBtnSubirBestia(ActionListener listener) {
    btnSubirBestia.addActionListener(listener);
  }

  public void listenerBtnBajarBestia(ActionListener listener) {
    btnBajarBestia.addActionListener(listener);
  }

  public void listenerBtnEliminarBestia(ActionListener listener) {
    btnEliminarBestia.addActionListener(listener);
  }

  // Getters campos
  public String getNombreHeroe() {
    return txtNombreHeroe.getText();
  }

  public String getTipoHeroe() {
    return (String) cbTipoHeroe.getSelectedItem();
  }

  public String getVidaHeroe() {
    return txtVidaHeroe.getText();
  }

  public String getArmaduraHeroe() {
    return txtArmaduraHeroe.getText();
  }

  public String getNombreBestia() {
    return txtNombreBestia.getText();
  }

  public String getTipoBestia() {
    return (String) cbTipoBestia.getSelectedItem();
  }

  public String getVidaBestia() {
    return txtVidaBestia.getText();
  }

  public String getArmaduraBestia() {
    return txtArmaduraBestia.getText();
  }

  // Interacción con lista
  public int getIndiceHeroeSeleccionado() {
    return listaHeroes.getSelectedIndex();
  }

  public int getIndiceBestiaSeleccionado() {
    return listaBestias.getSelectedIndex();
  }

  public void setIndiceHeroeSeleccionado(int index) {
    listaHeroes.setSelectedIndex(index);
  }

  public void setIndiceBestiaSeleccionado(int index) {
    listaBestias.setSelectedIndex(index);
  }

  // Actualizar lista
  public void anyadirHeroeLista(String texto) {
    modeloListaHeroes.addElement(texto);
  }

  public void anyadirBestiaLista(String texto) {
    modeloListaBestias.addElement(texto);
  }

  public void eliminarHeroeLista(int index) {
    modeloListaHeroes.remove(index);
  }

  public void eliminarBestiaLista(int index) {
    modeloListaBestias.remove(index);
  }

  // Métodos que controlan elementos de la vista
  public void intercambiarHeroesLista(int i, int j) {
    String temp = modeloListaHeroes.get(i);
    modeloListaHeroes.set(i, modeloListaHeroes.get(j));
    modeloListaHeroes.set(j, temp);
  }

  public void intercambiarBestiasLista(int i, int j) {
    String temp = modeloListaBestias.get(i);
    modeloListaBestias.set(i, modeloListaBestias.get(j));
    modeloListaBestias.set(j, temp);
  }

  public void limpiarFormularios() {
    txtNombreHeroe.setText("");
    txtVidaHeroe.setText("");
    txtArmaduraHeroe.setText("");
    txtNombreBestia.setText("");
    txtVidaBestia.setText("");
    txtArmaduraBestia.setText("");
  }

  public void escribirLog(String texto) {
    txtLogBatalla.append(texto + "\n");
  }
}
