/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author juanj
 */
public class CombateTest {

    private Jugador j1;
    private Jugador j2;

    @BeforeEach
    void preparar() {
        j1 = new Jugador("Jugador 1", Combate.LP_INICIAL);
        j2 = new Jugador("Jugador 2", Combate.LP_INICIAL);
    }

    private Monstruo crear(Jugador dueno, String nombre, int atq, int def, Posicion pos) {
        Monstruo m = new Monstruo(nombre, atq, def, pos);
        dueno.agregarMonstruo(m);
        return m;
    }

    @Test
    void ataqueVsAtaqueGanaAtacante() {
        Monstruo a = crear(j1, "Dragon", 2500, 2000, Posicion.ATAQUE);
        Monstruo d = crear(j2, "Mago", 2000, 1000, Posicion.ATAQUE);
        ResultadoCombate r = Combate.atacar(j1, a, j2, d);
        assertEquals(500, r.getDano());
        assertEquals(7500, j2.getLp());
        assertEquals(8000, j1.getLp());
        assertFalse(j2.getCampo().contains(d));
        assertTrue(j1.getCampo().contains(a));
    }

    @Test
    void ataqueVsAtaquePierdeAtacante() {
        Monstruo a = crear(j1, "Dragon", 1500, 2000, Posicion.ATAQUE);
        Monstruo d = crear(j2, "Mago", 2000, 1000, Posicion.ATAQUE);
        Combate.atacar(j1, a, j2, d);
        assertEquals(7500, j1.getLp());
        assertEquals(8000, j2.getLp());
        assertFalse(j1.getCampo().contains(a));
        assertTrue(j2.getCampo().contains(d));
    }

    @Test
    void ataqueVsAtaqueEmpateDestruyeAmbos() {
        Monstruo a = crear(j1, "Dragon", 2000, 1000, Posicion.ATAQUE);
        Monstruo d = crear(j2, "Mago", 2000, 1000, Posicion.ATAQUE);
        Combate.atacar(j1, a, j2, d);
        assertEquals(8000, j1.getLp());
        assertEquals(8000, j2.getLp());
        assertTrue(j1.getCampo().isEmpty());
        assertTrue(j2.getCampo().isEmpty());
    }

    @Test
    void ataqueVsDefensaGanaAtacanteNoHayDanoLp() {
        Monstruo a = crear(j1, "Dragon", 2500, 2000, Posicion.ATAQUE);
        Monstruo d = crear(j2, "Muro", 1000, 1500, Posicion.DEFENSA);
        ResultadoCombate r = Combate.atacar(j1, a, j2, d);
        assertEquals(0, r.getDano());
        assertEquals(8000, j2.getLp());
        assertFalse(j2.getCampo().contains(d));
    }

    @Test
    void ataqueVsDefensaPierdeAtacanteRecibeDano() {
        Monstruo a = crear(j1, "Dragon", 1000, 2000, Posicion.ATAQUE);
        Monstruo d = crear(j2, "Muro", 500, 2000, Posicion.DEFENSA);
        ResultadoCombate r = Combate.atacar(j1, a, j2, d);
        assertEquals(1000, r.getDano());
        assertEquals(7000, j1.getLp());
        assertTrue(j1.getCampo().contains(a));
        assertTrue(j2.getCampo().contains(d));
    }

    @Test
    void ataqueVsDefensaEmpateNoPasaNada() {
        Monstruo a = crear(j1, "Dragon", 2000, 2000, Posicion.ATAQUE);
        Monstruo d = crear(j2, "Muro", 500, 2000, Posicion.DEFENSA);
        ResultadoCombate r = Combate.atacar(j1, a, j2, d);
        assertEquals(0, r.getDano());
        assertEquals(8000, j1.getLp());
        assertEquals(8000, j2.getLp());
        assertEquals(1, j1.getCampo().size());
        assertEquals(1, j2.getCampo().size());
    }

    @Test
    void ataqueDirectoRestaLp() {
        Monstruo a = crear(j1, "Dragon", 3000, 2000, Posicion.ATAQUE);
        ResultadoCombate r = Combate.atacar(j1, a, j2);
        assertEquals(3000, r.getDano());
        assertEquals(5000, j2.getLp());
    }

    @Test
    void ataqueDirectoConDefensoresLanzaExcepcion() {
        Monstruo a = crear(j1, "Dragon", 3000, 2000, Posicion.ATAQUE);
        crear(j2, "Muro", 500, 2000, Posicion.DEFENSA);
        assertThrows(IllegalStateException.class, () -> Combate.atacar(j1, a, j2));
    }

    @Test
    void monstruoEnDefensaNoPuedeAtacar() {
        Monstruo a = crear(j1, "Dragon", 3000, 2000, Posicion.DEFENSA);
        assertThrows(IllegalArgumentException.class, () -> Combate.atacar(j1, a, j2));
    }

    @Test
    void monstruoEfectoSumaBonoAlAtaque() {
        MonstruoEfecto m = new MonstruoEfecto("Mago Oscuro", 2000, 1500, Posicion.ATAQUE, 300);
        assertEquals(2300, m.getAtaque());
        assertEquals("Monstruo de Efecto", m.getTipo());
    }
}
