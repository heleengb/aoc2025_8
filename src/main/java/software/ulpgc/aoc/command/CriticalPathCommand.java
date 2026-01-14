package software.ulpgc.aoc.command;

import software.ulpgc.aoc.model.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Parte 2: Valor de la conexión crítica que unifica el sistema
public class CriticalPathCommand implements AnalysisCommand {

    @Override
    public long execute(List<Point3D> points) {
        int n = points.size();
        NetworkTopology topology = new NetworkTopology(n);

        // Generar TODAS las conexiones posibles y ordenar
        List<TransmissionLink> links = IntStream.range(0, n).boxed()
                .flatMap(i -> IntStream.range(i + 1, n)
                        .mapToObj(j -> {
                            Point3D p1 = points.get(i);
                            Point3D p2 = points.get(j);
                            return new TransmissionLink(p1, p2, p1.distanceSquaredTo(p2));
                        }))
                .sorted()
                .collect(Collectors.toList());

        // Ejecutar Kruskal hasta que solo quede 1 componente
        for (TransmissionLink link : links) {
            boolean merged = topology.connect(link.u().id(), link.v().id());

            if (merged && topology.getComponentCount() == 1) {
                // Encontrada la conexión crítica
                return (long) link.u().x() * link.v().x();
            }
        }
        return -1;
    }
}