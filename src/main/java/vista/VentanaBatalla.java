/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import modelo.Monstruo;

/**
 *
 * @author juanj
 */

public class VentanaBatalla extends JFrame {

    private JTextField txtNombre = new JTextField(10);
    private JTextField txtAtq = new JTextField(5);
    private JTextField txtDef = new JTextField(5);
    private JTextField txtBono = new JTextField(5);
    private JComboBox<String> cmbPosicion = new JComboBox<>(new String[]{"Ataque", "Defensa"});
    private JComboBox<String> cmbDueno = new JComboBox<>(new String[]{"Jugador 1", "Jugador 2"});
    private JButton btnAgregar = new JButton("Invocar monstruo");

    private JLabel lblLp1 = new JLabel("Jugador 1 - LP: 0");
    private JLabel lblLp2 = new JLabel("Jugador 2 - LP: 0");
    private JComboBox<Monstruo> cmbMonstruosJ1 = new JComboBox<>();
    private JComboBox<Monstruo> cmbMonstruosJ2 = new JComboBox<>();
    private JCheckBox chkDirecto = new JCheckBox("Ataque directo");
    private JButton btnJ1Ataca = new JButton("J1 ataca a J2");
    private JButton btnJ2Ataca = new JButton("J2 ataca a J1");

    private JButton btnReporte = new JButton("Reporte de monstruos");
    private JButton btnHistorial = new JButton("Ver historial (archivo)");
    private JTextArea txtLog = new JTextArea(12, 50);

    public VentanaBatalla() {
        setTitle("Yu-Gi-Oh! - Fase de Batalla");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Panel superior crear monstruo
        JPanel pnlCrear = new JPanel(new FlowLayout());
        pnlCrear.setBorder(BorderFactory.createTitledBorder("Crear monstruo"));
        pnlCrear.add(new JLabel("Nombre:"));
        pnlCrear.add(txtNombre);
        pnlCrear.add(new JLabel("ATQ:"));
        pnlCrear.add(txtAtq);
        pnlCrear.add(new JLabel("DEF:"));
        pnlCrear.add(txtDef);
        pnlCrear.add(new JLabel("Bono ATQ (opcional):"));
        pnlCrear.add(txtBono);
        pnlCrear.add(cmbPosicion);
        pnlCrear.add(cmbDueno);
        pnlCrear.add(btnAgregar);
        add(pnlCrear, BorderLayout.NORTH);

        // Panel central batalla
        JPanel pnlBatalla = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlBatalla.setBorder(BorderFactory.createTitledBorder("Batalla"));
        lblLp1.setForeground(new Color(0, 100, 0));
        lblLp2.setForeground(new Color(150, 0, 0));
        pnlBatalla.add(lblLp1);
        pnlBatalla.add(lblLp2);
        pnlBatalla.add(cmbMonstruosJ1);
        pnlBatalla.add(cmbMonstruosJ2);
        JPanel pnlBotones = new JPanel(new FlowLayout());
        pnlBotones.add(chkDirecto);
        pnlBotones.add(btnJ1Ataca);
        pnlBotones.add(btnJ2Ataca);
        pnlBatalla.add(pnlBotones);
        JPanel pnlReportes = new JPanel(new FlowLayout());
        pnlReportes.add(btnReporte);
        pnlReportes.add(btnHistorial);
        pnlBatalla.add(pnlReportes);
        add(pnlBatalla, BorderLayout.CENTER);

        // Panel inferior: registro
        txtLog.setEditable(false);
        add(new JScrollPane(txtLog), BorderLayout.SOUTH);

        pack();
    }

    // Métodos de ayuda para el controlador
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void agregarLog(String texto) {
        txtLog.append(texto + "\n");
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtAtq.setText("");
        txtDef.setText("");
        txtBono.setText("");
    }

    public void habilitarAtaques(boolean habilitar) {
        btnJ1Ataca.setEnabled(habilitar);
        btnJ2Ataca.setEnabled(habilitar);
    }

    // Getters 
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtAtq() { return txtAtq; }
    public JTextField getTxtDef() { return txtDef; }
    public JTextField getTxtBono() { return txtBono; }
    public JComboBox<String> getCmbPosicion() { return cmbPosicion; }
    public JComboBox<String> getCmbDueno() { return cmbDueno; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JLabel getLblLp1() { return lblLp1; }
    public JLabel getLblLp2() { return lblLp2; }
    public JComboBox<Monstruo> getCmbMonstruosJ1() { return cmbMonstruosJ1; }
    public JComboBox<Monstruo> getCmbMonstruosJ2() { return cmbMonstruosJ2; }
    public JCheckBox getChkDirecto() { return chkDirecto; }
    public JButton getBtnJ1Ataca() { return btnJ1Ataca; }
    public JButton getBtnJ2Ataca() { return btnJ2Ataca; }
    public JButton getBtnReporte() { return btnReporte; }
    public JButton getBtnHistorial() { return btnHistorial; }
}
