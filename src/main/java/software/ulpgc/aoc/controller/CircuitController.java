package software.ulpgc.aoc.controller;

import software.ulpgc.aoc.command.CriticalPathCommand;
import software.ulpgc.aoc.command.PowerConsumptionCommand;
import software.ulpgc.aoc.model.Point3D;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CircuitController {

    // parsear líneas a puntos
    private List<Point3D> parsePoints(List<String> rawData) {
        AtomicInteger idGen = new AtomicInteger(0);
        return rawData.stream()
                .map(line -> {
                    String[] parts = line.split(",");
                    return new Point3D(
                            idGen.getAndIncrement(),
                            Integer.parseInt(parts[0]),
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2])
                    );
                })
                .collect(Collectors.toList());
    }

    public long analyzeCircuitPower(List<String> data) {
        List<Point3D> points = parsePoints(data);
        return new PowerConsumptionCommand().execute(points);
    }

    public long analyzeCriticalConnection(List<String> data) {
        List<Point3D> points = parsePoints(data);
        return new CriticalPathCommand().execute(points);
    }
}