package com.github.alhkam.lotrbattlesimulator.servicio;

import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Bestia;
import com.github.alhkam.lotrbattlesimulator.modelo.personaje.Heroe;

import java.util.ArrayList;
import java.util.List;

public class ServicioBatalla {

    private final List<Heroe> ejercitoHeroes;
    private final List<Bestia> ejercitoBestias;

    public ServicioBatalla(List<Heroe> ejercitoHeroes, List<Bestia> ejercitoBestias) {
        this.ejercitoHeroes = ejercitoHeroes;
        this.ejercitoBestias = ejercitoBestias;
    }

    public void ejecutarBatalla() {
        int turno = 1;

        while (!condicionVictoria()) {
            System.out.println("Turno " + turno + ":");
            ejecutarTurno();
            turno++;
        }

        if (this.ejercitoHeroes.isEmpty()) {
            System.out.println("¡¡VICTORIA DE LAS BESTIAS!!");
        } else {
            System.out.println("¡¡VICTORIA DE LOS HÉROES!!");
        }
    }

    private void ejecutarTurno() {
        int miembrosEjercitoMenor = Math.min(this.ejercitoHeroes.size(), this.ejercitoBestias.size());

        List<Integer> muertosHeroes = new ArrayList<>();
        List<Integer> muertosBestias = new ArrayList<>();

        // Se ejecuta turno combate
        for (int i = 0; i < miembrosEjercitoMenor; i++) {

            Heroe heroe = ejercitoHeroes.get(i);
            Bestia bestia = ejercitoBestias.get(i);

            ejecutarCombate(heroe, bestia);

            if (heroe.estaMuerto()) {
                System.out.println("\t¡Muere " + heroe.getClass().getSimpleName() + " "
                        + heroe.getNombre() + "!");
                muertosHeroes.add(i);
            }

            if (bestia.estaMuerto()) {
                System.out.println("\t¡Muere " + bestia.getClass().getSimpleName() + " "
                        + bestia.getNombre() + "!");
                muertosBestias.add(i);
            }
        }

        // Se eliminan los combatientes fallecidos
        for (int i = muertosHeroes.size() - 1; i >= 0; i--) {
            int posicionMuerto = muertosHeroes.get(i);
            ejercitoHeroes.remove(posicionMuerto);
        }

        for (int i = muertosBestias.size() - 1; i >= 0; i--) {
            int posicionMuerto = muertosBestias.get(i);
            ejercitoBestias.remove(posicionMuerto);
        }

    }

    private void ejecutarCombate(Heroe heroe, Bestia bestia) {
        System.out.printf(
                "\tLucha entre %s (Vida=%d Armadura=%d) y %s (Vida=%d Armadura=%d)\n",
                heroe.getNombre(),
                heroe.getPuntosVida(),
                heroe.getNivelArmadura(),
                bestia.getNombre(),
                bestia.getPuntosVida(),
                bestia.getNivelArmadura());
        heroe.atacar(bestia);
        bestia.atacar(heroe);
    }

    private boolean condicionVictoria() {
        return this.ejercitoHeroes.isEmpty() || this.ejercitoBestias.isEmpty();
    }
}
