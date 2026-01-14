package software.ulpgc.aoc.command;

import software.ulpgc.aoc.model.Point3D;
import java.util.List;

public interface AnalysisCommand {
    long execute(List<Point3D> points);
}