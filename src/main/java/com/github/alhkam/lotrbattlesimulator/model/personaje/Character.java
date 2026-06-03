package com.github.alhkam.lotrbattlesimulator.model.personaje;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Character {

    private String name;
    private int lifePoints;
    private int armorLevel;

    public abstract void attack(Character character);

    public void takeDamage(int damage){
        this.lifePoints = this.lifePoints - damage;
    }

    public boolean isAlive() {
        return this.lifePoints >= 0;
    }
}
