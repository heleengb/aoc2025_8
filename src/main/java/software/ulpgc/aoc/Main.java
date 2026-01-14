package software.ulpgc.aoc;

import software.ulpgc.aoc.controller.CircuitController;
import software.ulpgc.aoc.io.FileInputReader;
import software.ulpgc.aoc.view.ConsoleResultPrinter;

import java.nio.file.Path;

public class Main {
    private static final Path INPUT_PATH = Path.of("src", "main", "resources", "circuitos.txt");

    public static void main(String[] args) {
        try {
            // IO
            var rawData = new FileInputReader(INPUT_PATH).readContent();

            // CONTROLLER
            long result = new CircuitController().analyzeCircuitPower(rawData);

            // VIEW
            new ConsoleResultPrinter().display("Parte 1: Potencia de Circuitos", result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}