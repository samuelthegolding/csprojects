import java.util.*;

public class Graph {
    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final String name; //The file from which the graph was created.
    private int[][] residuals;
    private int[] parent;

    public Graph(String name, int vertexCount) {
        this.name = name;

        vertices = new GraphNode[vertexCount];
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
        }
    }

    public boolean addEdge(int source, int destination, int capacity) {
        // A little bit of validation
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        // This adds the actual requested edge, along with its capacity
        vertices[source].addEdge(source, destination, capacity);

        // TODO: This is what you have to describe in the required README.TXT file
        //       that you submit as part of this assignment.
        vertices[destination].addEdge(destination, source, 0);

        return true;
    }

    /**
     * Algorithm to find max-flow in a network
     */
    public int findMaxFlow(int s, int t, boolean report) {
        System.out.println("\n-- Max Flow: " + name + "--");

        int vCount = vertices.length;
        residuals = new int[vCount][vCount];
        parent = new int[vCount];

        for (GraphNode node : vertices) {
            for (GraphNode.EdgeInfo edge : node.successor) {
                residuals[edge.from][edge.to] = edge.capacity;
            }
        }

        // "Set Total Flow to 0"
        int flow = 0;

        // "While there is an Augmenting Path between s and t"
        while (hasAugmentingPath(s, t)) {

            // "set the available flow to the largest possible integer that Java can represent"
            int flowTemp = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int i = parent[v];
                flowTemp = Math.min(flowTemp, residuals[i][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residuals[u][v] -= flowTemp;
                residuals[v][u] += flowTemp;
            }

            flow += flowTemp;

            if (report) {
                List<Integer> path = new ArrayList<>();
                for (int v = t; v != -1; v = parent[v]) path.add(v);
                Collections.reverse(path);
                System.out.print("Flow " + flow + ": ");
                for (int node : path) System.out.print(node + " ");
                System.out.println();
            }

        }
        System.out.println();

        if (report) {
            for (GraphNode node : vertices) {
                for (GraphNode.EdgeInfo edge : node.successor) {
                    int u = edge.from;
                    int v = edge.to;
                    int used = edge.capacity - residuals[u][v];
                    if (used > 0 && edge.capacity > 0) {
                        System.out.println("Edge(" + u + ", " + v + ") transports " + used + " items");
                    }

                }
            }
        }
        return flow;
    }

    /**
     * Algorithm to find an augmenting path in a network
     */
    private boolean hasAugmentingPath(int s, int t) {
        boolean[] visited = new boolean[vertices.length];
        boolean hasPath = false;
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(parent, -1);
        queue.add(s);
        visited[s] = true;
        whileLoop:
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (GraphNode.EdgeInfo edge : vertices[u].successor) {
                int v = edge.to;
                if (!visited[v] && residuals[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    queue.add(v);
                    if (v == t) {
                        hasPath = true;
                        break whileLoop;
                    }
                }
            }
        }
        return hasPath;
    }

    /**
     * Algorithm to find the min-cut edges in a network
     */
    public void findMinCut(int s) {
        System.out.println("\n-- MinCut: " + name + "--");
        boolean[] visited = new boolean[vertices.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (GraphNode.EdgeInfo edge : vertices[u].successor) {
                int v = edge.to;
                if (!visited[v] && residuals[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }

        Set<String> edges = new HashSet<>();
        for (int u = 0; u < vertices.length; u++) {
            if (visited[u]) {
                for (GraphNode.EdgeInfo edge : vertices[u].successor) {
                    int v = edge.to;
                    if (!visited[v] && edge.capacity > 0) {
                        String edgeKey = String.format("(%d, %d)", u, v);
                        if (!edges.contains(edgeKey)) {
                            System.out.printf("Min Cut Edge %s\n", edgeKey);
                            edges.add(edgeKey);
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("The Graph ").append(name).append(" \n");
        for (var vertex : vertices) {
            sb.append((vertex.toString()));
        }
        return sb.toString();
    }
}
