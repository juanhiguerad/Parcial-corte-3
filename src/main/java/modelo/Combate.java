/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juanj
 */
public class Combate {

    public static final int LP_INICIAL = 8000;

    /* Ataque contra un monstruo defensor */
    public static ResultadoCombate atacar(Jugador jAtacante, Monstruo atacante,
                                          Jugador jDefensor, Monstruo defensor) {
        validarAtacante(atacante);

        String nomAtacante = atacante.getNombre() + " (" + jAtacante.getNombre() + ")";
        String nomDefensor = defensor.getNombre() + " (" + jDefensor.getNombre() + ")";
        int dano = 0;
        String resultado;

        if (defensor.estaEnAtaque()) {
            // ataque vs ataque
            int diferencia = atacante.getAtaque() - defensor.getAtaque();
            if (diferencia > 0) {
                dano = diferencia;
                jDefensor.recibirDano(dano);
                jDefensor.eliminarMonstruo(defensor);
                resultado = "Defensor destruido. " + jDefensor.getNombre() + " pierde " + dano + " LP";
            } else if (diferencia < 0) {
                dano = -diferencia;
                jAtacante.recibirDano(dano);
                jAtacante.eliminarMonstruo(atacante);
                resultado = "Atacante destruido. " + jAtacante.getNombre() + " pierde " + dano + " LP";
            } else {
                jAtacante.eliminarMonstruo(atacante);
                jDefensor.eliminarMonstruo(defensor);
                resultado = "Ambos monstruos destruidos. Nadie pierde LP";
            }
        } else {
            // ataque vs defensa
            int diferencia = atacante.getAtaque() - defensor.getDefensa();
            if (diferencia > 0) {
                jDefensor.eliminarMonstruo(defensor);
                resultado = "Defensor destruido. Nadie pierde LP";
            } else if (diferencia < 0) {
                dano = -diferencia;
                jAtacante.recibirDano(dano);
                resultado = "Ningún monstruo destruido. " + jAtacante.getNombre() + " pierde " + dano + " LP";
            } else {
                resultado = "Ningún monstruo destruido. Nadie pierde LP";
            }
        }
        return new ResultadoCombate(nomAtacante, nomDefensor, dano, resultado);
    }

    /* Ataque directo, solo si el oponente no tiene monstruos */
    public static ResultadoCombate atacar(Jugador jAtacante, Monstruo atacante, Jugador jDefensor) {
        validarAtacante(atacante);
        if (!jDefensor.getCampo().isEmpty()) {
            throw new IllegalStateException("No se puede atacar directo: el oponente tiene monstruos");
        }
        int dano = atacante.getAtaque();
        jDefensor.recibirDano(dano);
        return new ResultadoCombate(
                atacante.getNombre() + " (" + jAtacante.getNombre() + ")",
                jDefensor.getNombre() + " (ataque directo)",
                dano,
                jDefensor.getNombre() + " pierde " + dano + " LP");
    }

    private static void validarAtacante(Monstruo atacante) {
        if (!atacante.estaEnAtaque()) {
            throw new IllegalArgumentException("Solo un monstruo en posición de ataque puede atacar");
        }
    }
}
