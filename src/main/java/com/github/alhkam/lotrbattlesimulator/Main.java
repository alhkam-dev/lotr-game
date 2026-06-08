package com.github.alhkam.lotrbattlesimulator;

import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Orco;
import com.github.alhkam.lotrbattlesimulator.modelo.bestias.Trasgo;
import com.github.alhkam.lotrbattlesimulator.modelo.heroes.Elfo;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;
import com.github.alhkam.lotrbattlesimulator.servicio.ServicioBatalla;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Heroe> ejercitoHeroes = new ArrayList<>();
    List<Bestia> ejercitoBestias = new ArrayList<>();

    // Crea el ejercito de heroes
    ejercitoHeroes.add(new Elfo("Légolas", 150, 30));
    ejercitoHeroes.add(new Elfo("Aragorn", 150, 50));
    ejercitoHeroes.add(new Elfo("Boromir", 100, 60));
    ejercitoHeroes.add(new Elfo("Gandalf", 300, 30));
    ejercitoHeroes.add(new Elfo("Frodo", 20, 10));

    // Crea el ejercito de bestias
    ejercitoBestias.add(new Orco("Lurtz", 200, 60));
    ejercitoBestias.add(new Orco("Shagrat", 220, 50));
    ejercitoBestias.add(new Trasgo("Uglúk", 120, 30));
    ejercitoBestias.add(new Orco("Mauhúr", 100, 30));

    ServicioBatalla servicioBatalla = new ServicioBatalla(ejercitoHeroes, ejercitoBestias);
    servicioBatalla.ejecutarBatalla();
  }
}
