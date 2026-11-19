/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 *
 * @author juanj
 */
public class JugadorTest {

    @Test
    void lpInicialCorrecto() {
        Jugador j = new Jugador("Yugi", 8000);
        assertEquals(8000, j.getLp());
        assertFalse(j.estaDerrotado());
    }

    @Test
    void recibirDanoRestaLp() {
        Jugador j = new Jugador("Yugi", 8000);
        j.recibirDano(1500);
        assertEquals(6500, j.getLp());
    }

    @Test
    void lpNuncaEsNegativo() {
        Jugador j = new Jugador("Yugi", 1000);
        j.recibirDano(5000);
        assertEquals(0, j.getLp());
        assertTrue(j.estaDerrotado());
    }

    @Test
    void danoNegativoLanzaExcepcion() {
        Jugador j = new Jugador("Yugi", 8000);
        assertThrows(IllegalArgumentException.class, () -> j.recibirDano(-100));
    }
}
