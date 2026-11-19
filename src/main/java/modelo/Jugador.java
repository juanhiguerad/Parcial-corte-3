/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author juanj
 */
/* Jugador con Puntos de Vida (LP) y monstruos en el campo */
public class Jugador {

    private String nombre;
    private int lp;
    private ArrayList<Monstruo> campo = new ArrayList<>();

    public Jugador(String nombre, int lpInicial) {
        this.nombre = nombre;
        this.lp = lpInicial;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLp() {
        return lp;
    }

    public ArrayList<Monstruo> getCampo() {
        return campo;
    }

    /* Resta LP y los LP nunca bajan de 0 */
    public void recibirDano(int dano) {
        if (dano < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo");
        }
        lp = lp - dano;
        if (lp < 0) {
            lp = 0;
        }
    }

    public boolean estaDerrotado() {
        return lp == 0;
    }

    public void agregarMonstruo(Monstruo m) {
        campo.add(m);
    }

    public boolean eliminarMonstruo(Monstruo m) {
        return campo.remove(m);
    }
}
