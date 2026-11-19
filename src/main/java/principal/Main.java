/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import controlador.ControladorBatalla;
import java.util.List;
import javax.swing.SwingUtilities;
import modelo.Jugador;
import modelo.Monstruo;
import modelo.ResultadoCombate;
import vista.VentanaBatalla;

/**
 *
 * @author juanj
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaBatalla vista = new VentanaBatalla();
            ControladorBatalla controlador = new ControladorBatalla(vista);
            controlador.iniciar();
        });
    }

    /* Reporte 1 monstruos de cada jugador (recorre ArrayList) */
    public static String reporteMonstruos(List<Jugador> jugadores) {
        StringBuilder sb = new StringBuilder("=== REPORTE 1: MONSTRUOS EN EL CAMPO ===\n");
        for (Jugador j : jugadores) {
            sb.append(j.getNombre()).append(" (LP ").append(j.getLp()).append(")\n");
            if (j.getCampo().isEmpty()) {
                sb.append("   Sin monstruos\n");
            }
            for (Monstruo m : j.getCampo()) {
                sb.append("   - ").append(m.getTipo()).append(": ").append(m).append("\n");
            }
        }
        return sb.toString();
    }

    /* Reporte 2, resumen de combates realizados (recorre ArrayList) */
    public static String reporteHistorial(List<ResultadoCombate> historial) {
        int danoTotal = 0;
        for (ResultadoCombate r : historial) {
            danoTotal += r.getDano();
        }
        StringBuilder sb = new StringBuilder("=== REPORTE 2: RESUMEN DE COMBATES ===\n");
        sb.append("Total de ataques: ").append(historial.size()).append("\n");
        sb.append("Daño total infligido: ").append(danoTotal).append(" LP\n");
        return sb.toString();
    }
}
