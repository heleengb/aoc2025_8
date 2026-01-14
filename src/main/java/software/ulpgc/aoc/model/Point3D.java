package software.ulpgc.aoc.model;

public record Point3D(int id, int x, int y, int z) {
    // Cálculo de distancia al cuadrado
    public double distanceSquaredTo(Point3D other) {
        return Math.pow(this.x - other.x, 2) +
                Math.pow(this.y - other.y, 2) +
                Math.pow(this.z - other.z, 2);
    }
}