package software.ulpgc.aoc.command;

import software.ulpgc.aoc.model.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Parte 1: Potencia de los 3 circuitos más grandes
public class PowerConsumptionCommand implements AnalysisCommand {
    private static final int CONNECTION_LIMIT = 1000;

    @Override
    public long execute(List<Point3D> points) {
        int n = points.size();
        NetworkTopology topology = new NetworkTopology(n);

        // Generar conexiones, ordenar y limitar a las 1000 más cercanas
        List<TransmissionLink> links = IntStream.range(0, n).boxed()
                .flatMap(i -> IntStream.range(i + 1, n)
                        .mapToObj(j -> {
                            Point3D p1 = points.get(i);
                            Point3D p2 = points.get(j);
                            return new TransmissionLink(p1, p2, p1.distanceSquaredTo(p2));
                        }))
                .sorted()
                .limit(CONNECTION_LIMIT)
                .collect(Collectors.toList());

        // Unir puntos
        for (TransmissionLink link : links) {
            topology.connect(link.u().id(), link.v().id());
        }

        // Calcular tamaños de grupos y obtener el producto de los mejores 3
        Map<Integer, Integer> groupSizes = IntStream.range(0, n).boxed()
                .collect(Collectors.toMap(
                        topology::findRoot,
                        topology::getGroupSize,
                        (a, b) -> a // ignorar duplicados de misma raiz
                ));

        return groupSizes.values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Integer::longValue)
                .reduce(1, (a, b) -> a * b);
    }
}