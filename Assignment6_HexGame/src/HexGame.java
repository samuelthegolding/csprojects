import java.util.ArrayList;
import java.util.Arrays;

public class HexGame {

    private final DisjointSet dSet;
    private final Color[] grid;
    private final int SIZE;
    private final int TOP_EDGE, BOTTOM_EDGE, LEFT_EDGE, RIGHT_EDGE;

    /**
     * Constructor of HexGame
     *
     * @param size Side Length
     */
    public HexGame(int size) {
        this.SIZE = size;
        this.dSet = new DisjointSet(SIZE * SIZE + 4);
        this.grid = new Color[SIZE * SIZE];

        Arrays.fill(grid, Color.NONE);

        //Edges
        this.TOP_EDGE = SIZE * SIZE;
        this.BOTTOM_EDGE = SIZE * SIZE + 1;
        this.LEFT_EDGE = SIZE * SIZE + 2;
        this.RIGHT_EDGE = SIZE * SIZE + 3;

    }

    /**
     * Play Methods that don't require another argument for color
     *
     * @param position         Position at which the "Player" plays the piece
     * @param displayNeighbors Boolean describing whether neighbors should be shown
     * @return True or False value if a player has won
     */
    public boolean playRed(int position, boolean displayNeighbors) {
        return play(position, Color.RED, displayNeighbors);
    }

    public boolean playBlue(int position, boolean displayNeighbors) {
        return play(position, Color.BLUE, displayNeighbors);
    }

    /**
     * Helper method which plays a piece at a position, as described above.
     * @param color Extra argument to choose color
     */
    private boolean play(int position, Color color, boolean displayNeighbors) {
        // Return False if Position is Invalid
        if (position < 1 || position > SIZE * SIZE || grid[position - 1] != Color.NONE) {
            return false;
        }

        // Changing position to be the color given
        grid[position - 1] = color;

        // Handle edge unions
        if (color == Color.RED) {
            if (position <= SIZE) dSet.union(position - 1, TOP_EDGE); // Top row for red
            if (position > SIZE * (SIZE - 1)) dSet.union(position - 1, BOTTOM_EDGE); // Bottom row for red
        } else if (color == Color.BLUE) {
            if (position % SIZE == SIZE - 1) dSet.union(position - 1, LEFT_EDGE); // Left edge for blue
            if (position % SIZE == 0) dSet.union(position - 1, RIGHT_EDGE); // Right edge for blue
        }

        // Find neighbors and perform unions
        ArrayList<Integer> neighbors = (color == Color.RED) ? getNeighborsRed(position) : getNeighborsBlue(position);
        for (int neighbor : neighbors) {
            if (neighbor > SIZE * SIZE) continue; // Skip if Out of Bounds
            if (grid[neighbor - 1] == color) dSet.union(position - 1, neighbor - 1);
        }

        // Check winning conditions
        if (color == Color.BLUE && dSet.find(LEFT_EDGE) == dSet.find(RIGHT_EDGE)) return true; // Blue wins
        return color == Color.RED && dSet.find(TOP_EDGE) == dSet.find(BOTTOM_EDGE); // Red wins
    }


    // Simple Getters for Grid and SIZE
    public Color[] getGrid() {
        return grid;
    }

    public int getSize() {
        return SIZE;
    }

    /**
     * Method to Get a Red position's neighbor
     * @param position Position from which to return the neighbors
     * @return a List of Integers
     */
    private ArrayList<Integer> getNeighborsRed(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;

        // Edge connections
        if (row == 0) neighbors.add(TOP_EDGE + 1);  // +1 to match 1-based position convention
        if (row == SIZE - 1) neighbors.add(BOTTOM_EDGE + 1);

        // Hex neighbors (1-based positions)
        if (col > 0) neighbors.add(position - 1);           // Left
        if (col < SIZE - 1) neighbors.add(position + 1);    // Right
        if (row > 0) neighbors.add(position - SIZE);        // Top-left
        if (row > 0 && col < SIZE - 1) neighbors.add(position - SIZE + 1); // Top-right
        if (row < SIZE - 1) neighbors.add(position + SIZE);  // Bottom-right
        if (row < SIZE - 1 && col > 0) neighbors.add(position + SIZE - 1); // Bottom-left

        return neighbors;
    }

    /**
     * Method to Get a Blue position's neighbor
     * @param position Position from which to return the neighbors
     * @return a List of Integers
     */
    private ArrayList<Integer> getNeighborsBlue(int position) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;

        // Edge connections
        if (col == 0) neighbors.add(LEFT_EDGE + 1);
        if (col == SIZE - 1) neighbors.add(RIGHT_EDGE + 1);

        // Hex neighbors (1-based positions)
        if (col > 0) neighbors.add(position - 1);           // Left
        if (col < SIZE - 1) neighbors.add(position + 1);    // Right
        if (row > 0) neighbors.add(position - SIZE);        // Top-left
        if (row > 0 && col < SIZE - 1) neighbors.add(position - SIZE + 1); // Top-right
        if (row < SIZE - 1) neighbors.add(position + SIZE);  // Bottom-right
        if (row < SIZE - 1 && col > 0) neighbors.add(position + SIZE - 1); // Bottom-left

        return neighbors;
    }

}
