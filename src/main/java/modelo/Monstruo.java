/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juanj
 */
/* Monstruo normal hereda de Carta e implementa Combatible. */
public class Monstruo extends Carta implements Combatible {

    private int atq;
    private int def;
    private Posicion posicion;

    public Monstruo(String nombre, int atq, int def, Posicion posicion) {
        super(nombre);
        this.atq = atq;
        this.def = def;
        this.posicion = posicion;
    }

    @Override
    public int getAtaque() {
        return atq;
    }

    @Override
    public int getDefensa() {
        return def;
    }

    @Override
    public boolean estaEnAtaque() {
        return posicion == Posicion.ATAQUE;
    }

    @Override
    public String getTipo() {
        return "Monstruo Normal";
    }

    public void setAtq(int atq) {
        this.atq = atq;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return getNombre() + " [ATQ " + getAtaque() + " / DEF " + getDefensa()
                + " / " + posicion + "]";
    }
}
