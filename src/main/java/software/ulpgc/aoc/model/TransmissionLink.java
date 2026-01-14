package software.ulpgc.aoc.model;

public record TransmissionLink(Point3D u, Point3D v, double distanceSq) implements Comparable<TransmissionLink> {
    @Override
    public int compareTo(TransmissionLink other) {
        return Double.compare(this.distanceSq, other.distanceSq);
    }
}