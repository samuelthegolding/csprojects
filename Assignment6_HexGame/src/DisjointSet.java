public class DisjointSet {
    private final int[] size;
    private final int[] parent;

    /**
     * Constructor making a 'Square' grid based on size
     *
     * @param size Integer side length
     */
    public DisjointSet(int size) {
        this.parent = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    /**
     * Union Operation on 2 Nodes
     */
    public void union(int node1, int node2) {
        // Find Roots
        int root1 = find(node1);
        int root2 = find(node2);

        // If they're already in the same set no union :)
        if (root1 == root2) return;

        // Merge by size
        if (size[root1] < size[root2]) {
            parent[root1] = root2; // Attach Root1 to Root2
            size[root2] += size[root1]; // Increment Size
        } else {
            // Do it backwards
            parent[root2] = root1;
            size[root1] += size[root2];
        }
    }


    public int find(int node) {
        // Error Handling
        if (node < 0 || node >= parent.length) throw new IllegalArgumentException("Invalid node index: " + node);
        // Recursively Find
        if (parent[node] != node) parent[node] = find(parent[node]);
        // Return when found b/c parent[node] = node
        return parent[node];
    }
}
