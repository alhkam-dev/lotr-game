package com.github.alhkam.lotrbattlesimulator.modelo.personaje;

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

    public int calcularDanyoRecibido(int poderAtaque, int armadura) {
        if(poderAtaque > armadura) {
            return poderAtaque - armadura;
        }
        return 0;
    }

    public int recibirDanyo(int poderAtaque, double multiplicadorArmadura) {
        int armaduraEfectiva = (int) (this.nivelArmadura * multiplicadorArmadura);

        int danyo = calcularDanyoRecibido(poderAtaque, armaduraEfectiva);

        if(danyo > 0){
            this.setPuntosVida(this.getPuntosVida() - danyo);
        }

        return danyo;
    }

    public boolean estaMuerto() {
        return this.puntosVida <= 0;
    }
}
