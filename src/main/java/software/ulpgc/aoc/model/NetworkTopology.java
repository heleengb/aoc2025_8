package software.ulpgc.aoc.model;

public class NetworkTopology {
    private final int[] parent;
    private final int[] size;
    private int componentCount;

    public NetworkTopology(int n) {
        this.componentCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findRoot(int i) {
        if (parent[i] != i) {
            parent[i] = findRoot(parent[i]); // Path compression
        }
        return parent[i];
    }

    // Devuelve true si la unión fue exitosa (eran grupos distintos)
    public boolean connect(int i, int j) {
        int rootI = findRoot(i);
        int rootJ = findRoot(j);

        if (rootI != rootJ) {
            // Union por size
            if (size[rootI] < size[rootJ]) {
                parent[rootI] = rootJ;
                size[rootJ] += size[rootI];
            } else {
                parent[rootJ] = rootI;
                size[rootI] += size[rootJ];
            }
            componentCount--;
            return true;
        }
        return false;
    }

    public int getGroupSize(int i) {
        return size[findRoot(i)];
    }

    public int getComponentCount() {
        return componentCount;
    }
}