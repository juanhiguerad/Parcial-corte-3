/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanj
 */
/* Guarda y lee el historial en historial_combate.txt. */
public class HistorialArchivo {

    public static final String NOMBRE_ARCHIVO = "historial_combate.txt";

    private final String ruta;

    public HistorialArchivo() {
        this(NOMBRE_ARCHIVO);
    }

    public HistorialArchivo(String ruta) {
        this.ruta = ruta;
    }

    public void guardar(ResultadoCombate resultado) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(ruta, StandardCharsets.UTF_8, true))) {
            bw.write(resultado.toString());
            bw.newLine();
        }
    }

    public List<String> leer() throws IOException {
        Path path = Path.of(ruta);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}