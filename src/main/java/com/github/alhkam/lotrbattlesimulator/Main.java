package com.github.alhkam.lotrbattlesimulator;

import com.github.alhkam.lotrbattlesimulator.model.personaje.Hero;

public class Main {
  public static void main(String[] args) {

    Hero hero = new Hero("Aragorn", 5, 5);
    Hero hero2 = new Hero("Aragorn", 5, 5);

    hero.attack(hero2);
  }
}
