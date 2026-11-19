/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juanj
 */
/* Guarda el resultado de un ataque (lo que se escribe en el historial). */
public class ResultadoCombate {

    private String atacante;
    private String defensor;
    private int dano;
    private String resultado;

    public ResultadoCombate(String atacante, String defensor, int dano, String resultado) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.dano = dano;
        this.resultado = resultado;
    }

    public String getAtacante() {
        return atacante;
    }

    public String getDefensor() {
        return defensor;
    }

    public int getDano() {
        return dano;
    }

    public String getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "Atacante: " + atacante + " | Defensor: " + defensor
                + " | Daño: " + dano + " | Resultado: " + resultado;
    }
}
