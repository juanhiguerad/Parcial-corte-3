/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import modelo.Combate;
import modelo.HistorialArchivo;
import modelo.Jugador;
import modelo.Monstruo;
import modelo.MonstruoEfecto;
import modelo.Posicion;
import modelo.ResultadoCombate;
import principal.Main;
import vista.VentanaBatalla;

/**
 *
 * @author juanj
 */
/* Controlador conecta la Vista con el modelo. */
public class ControladorBatalla {

    private final VentanaBatalla vista;
    private final Jugador jugador1;
    private final Jugador jugador2;
    private final HistorialArchivo archivo = new HistorialArchivo();
    private final ArrayList<ResultadoCombate> historial = new ArrayList<>();

    public ControladorBatalla(VentanaBatalla vista) {
        this.vista = vista;
        this.jugador1 = new Jugador("Jugador 1", Combate.LP_INICIAL);
        this.jugador2 = new Jugador("Jugador 2", Combate.LP_INICIAL);

        vista.getBtnAgregar().addActionListener(e -> agregarMonstruo());
        vista.getBtnJ1Ataca().addActionListener(e ->
                atacar(jugador1, jugador2, vista.getCmbMonstruosJ1(), vista.getCmbMonstruosJ2()));
        vista.getBtnJ2Ataca().addActionListener(e ->
                atacar(jugador2, jugador1, vista.getCmbMonstruosJ2(), vista.getCmbMonstruosJ1()));
        vista.getBtnReporte().addActionListener(e -> mostrarReportes());
        vista.getBtnHistorial().addActionListener(e -> verHistorial());

        actualizarVista();
    }

    public void iniciar() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    private void agregarMonstruo() {
        String nombre = vista.getTxtNombre().getText().trim();
        if (nombre.isEmpty()) {
            vista.mostrarMensaje("El nombre es obligatorio.");
            return;
        }
        try {
            int atq = Integer.parseInt(vista.getTxtAtq().getText().trim());
            int def = Integer.parseInt(vista.getTxtDef().getText().trim());
            int bono = 0;
            String textoBono = vista.getTxtBono().getText().trim();
            if (!textoBono.isEmpty()) {
                bono = Integer.parseInt(textoBono);
            }
            if (atq < 0 || def < 0 || bono < 0) {
                vista.mostrarMensaje("Los valores no pueden ser negativos.");
                return;
            }
            Posicion pos = vista.getCmbPosicion().getSelectedIndex() == 0
                    ? Posicion.ATAQUE : Posicion.DEFENSA;
            Monstruo m;
            if (bono > 0) {
                m = new MonstruoEfecto(nombre, atq, def, pos, bono);
            } else {
                m = new Monstruo(nombre, atq, def, pos);
            }
            Jugador dueno = vista.getCmbDueno().getSelectedIndex() == 0 ? jugador1 : jugador2;
            dueno.agregarMonstruo(m);
            vista.agregarLog(dueno.getNombre() + " invoca a " + m);
            vista.limpiarCampos();
            actualizarVista();
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("ATQ, DEF y bono deben ser números enteros.");
        }
    }

    private void atacar(Jugador jAtacante, Jugador jDefensor,
                        JComboBox<Monstruo> cmbAtacante, JComboBox<Monstruo> cmbDefensor) {
        Monstruo atacante = (Monstruo) cmbAtacante.getSelectedItem();
        if (atacante == null) {
            vista.mostrarMensaje(jAtacante.getNombre() + " no tiene monstruos para atacar.");
            return;
        }
        ResultadoCombate resultado;
        try {
            if (vista.getChkDirecto().isSelected()) {
                resultado = Combate.atacar(jAtacante, atacante, jDefensor);
            } else {
                Monstruo defensor = (Monstruo) cmbDefensor.getSelectedItem();
                if (defensor == null) {
                    vista.mostrarMensaje("El oponente no tiene monstruos. Usa ataque directo.");
                    return;
                }
                resultado = Combate.atacar(jAtacante, atacante, jDefensor, defensor);
            }
        } catch (IllegalArgumentException | IllegalStateException ex) {
            vista.mostrarMensaje(ex.getMessage());
            return;
        }

        historial.add(resultado);
        vista.agregarLog(resultado.toString());
        try {
            archivo.guardar(resultado);
        } catch (IOException ex) {
            vista.mostrarMensaje("No se pudo guardar en el historial: " + ex.getMessage());
        }
        actualizarVista();
        verificarGanador();
    }

    private void verificarGanador() {
        if (jugador1.estaDerrotado() || jugador2.estaDerrotado()) {
            String ganador = jugador1.estaDerrotado() ? jugador2.getNombre() : jugador1.getNombre();
            vista.mostrarMensaje("¡Fin del duelo! Gana " + ganador);
            vista.habilitarAtaques(false);
        }
    }

    private void mostrarReportes() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        vista.agregarLog(Main.reporteMonstruos(jugadores));
        vista.agregarLog(Main.reporteHistorial(historial));
    }

    private void verHistorial() {
        try {
            vista.agregarLog("--- Historial guardado en archivo ---");
            for (String linea : archivo.leer()) {
                vista.agregarLog(linea);
            }
        } catch (IOException ex) {
            vista.mostrarMensaje("No se pudo leer el historial: " + ex.getMessage());
        }
    }

    private void actualizarVista() {
        vista.getLblLp1().setText(jugador1.getNombre() + " - LP: " + jugador1.getLp());
        vista.getLblLp2().setText(jugador2.getNombre() + " - LP: " + jugador2.getLp());
        recargarCombo(vista.getCmbMonstruosJ1(), jugador1);
        recargarCombo(vista.getCmbMonstruosJ2(), jugador2);
    }

    private void recargarCombo(JComboBox<Monstruo> combo, Jugador jugador) {
        combo.removeAllItems();
        for (Monstruo m : jugador.getCampo()) {
            combo.addItem(m);
        }
    }
}
