package com.github.alhkam.lotrbattlesimulator.model.personaje;


public class Hero extends Character {

    public Hero(String name, int lifePoints, int armorLevel) {
        super(name, lifePoints, armorLevel);
    }

    @Override
    public void attack(Character character) {
        int numberDie1 = (int) (Math.random() * 100);
        int numberDie2 = (int) (Math.random() * 100);

        System.out.println(numberDie1);
    }
}
