/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juanj
 */
/* Monstruo de efecto suma un bono a su atq */
public class MonstruoEfecto extends Monstruo {

    private int bonoAtaque;

    public MonstruoEfecto(String nombre, int atq, int def, Posicion posicion, int bonoAtaque) {
        super(nombre, atq, def, posicion);
        this.bonoAtaque = bonoAtaque;
    }

    public int getBonoAtaque() {
        return bonoAtaque;
    }

    /* Sobreescritura el atq real incluye el bono del efecto */
    @Override
    public int getAtaque() {
        return super.getAtaque() + bonoAtaque;
    }

    @Override
    public String getTipo() {
        return "Monstruo de Efecto";
    }

    @Override
    public String toString() {
        return super.toString() + " (+" + bonoAtaque + " ATQ por efecto)";
    }
}
