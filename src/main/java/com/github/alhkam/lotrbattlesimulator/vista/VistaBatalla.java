package com.github.alhkam.lotrbattlesimulator.vista;


import javax.swing.*;
import java.awt.*;

public class VistaBatalla extends JFrame {

    public VistaBatalla() {
        setTitle("Batalla por la Tierra Media");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        //Panel superior
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));

        panelSuperior.add(crearPanelHeroes());
        panelSuperior.add(crearPanelBestias());

        add(panelSuperior, BorderLayout.NORTH);

        //Panel central
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createTitledBorder("Lucha"));

        JPanel panelListas = new JPanel(new GridLayout(1, 2, 10, 10));

        panelListas.add(crearPanelLista("Héroes"));
        panelListas.add(crearPanelLista("Bestias"));

        panelCentro.add(panelListas, BorderLayout.CENTER);

        // Botón lucha
        JButton btnLucha = new JButton("¡LUCHA!");
        panelCentro.add(btnLucha, BorderLayout.SOUTH);

        add(panelCentro, BorderLayout.CENTER);

        //Texto batalla
        JTextArea txtLog = new JTextArea();
        txtLog.setEditable(false);

        add(new JScrollPane(txtLog), BorderLayout.SOUTH);
    }

    private JPanel crearPanelHeroes() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Héroes"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        c.gridx = 0; c.gridy = 0;
        panel.add(new JLabel("Nombre:"), c);
        c.gridx = 1;
        panel.add(new JTextField(10), c);

        // Tipo
        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Tipo:"), c);
        c.gridx = 1;
        panel.add(new JComboBox<>(new String[]{"Elfos", "Humanos", "Hobbits"}), c);

        // Vida
        c.gridx = 0; c.gridy = 2;
        panel.add(new JLabel("Vida:"), c);
        c.gridx = 1;
        panel.add(new JTextField(5), c);

        // Armadura
        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Armadura:"), c);
        c.gridx = 1;
        panel.add(new JTextField(5), c);

        // Botón
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        panel.add(new JButton("Añadir"), c);

        return panel;
    }

    private JPanel crearPanelBestias() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Bestias"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        c.gridx = 0; c.gridy = 0;
        panel.add(new JLabel("Nombre:"), c);
        c.gridx = 1;
        panel.add(new JTextField(10), c);

        // Tipo
        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Tipo:"), c);
        c.gridx = 1;
        panel.add(new JComboBox<>(new String[]{"Orcos", "Trasgos"}), c);

        // Vida
        c.gridx = 0; c.gridy = 2;
        panel.add(new JLabel("Vida:"), c);
        c.gridx = 1;
        panel.add(new JTextField(5), c);

        // Armadura
        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Armadura:"), c);
        c.gridx = 1;
        panel.add(new JTextField(5), c);

        // Botón
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        panel.add(new JButton("Añadir"), c);

        return panel;
    }

    private JPanel crearPanelLista(String titulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> lista = new JList<>(modelo);

        panel.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.add(new JButton("Subir"));
        botones.add(new JButton("Bajar"));
        botones.add(new JButton("Eliminar"));

        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }
}

