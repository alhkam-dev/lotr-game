package com.github.alhkam.lotrbattlesimulator.model.personaje;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Personaje {

    private String nombre;
    private int puntosVida;
    private int nivelArmadura;

    public abstract void atacar(Personaje personaje);

    public void recibirDanyo(int poderAtaque) {
        if (poderAtaque > this.nivelArmadura) {
            int danyo = poderAtaque - this.nivelArmadura;
            this.puntosVida = this.puntosVida - danyo;

            System.out.println("Attack power '" + poderAtaque + "' was superior than armor level '" + this.nivelArmadura
                    + "'. The danyo received was " + danyo);
        } else {
            System.out.println(this.nombre + " did not take damage");
        }
    }

    public boolean estaVivo() {
        return this.puntosVida >= 0;
    }
}
