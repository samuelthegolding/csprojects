public class LifeSimulator {
    int sizeX;
    int sizeY;
    boolean[][] simGrid;

    public LifeSimulator(int sizeX, int sizeY) {
        this.simGrid = new boolean[sizeX][sizeY];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean getCell(int x, int y) {
        return simGrid[x][y];
    }

    public void insertPattern(Pattern pattern, int startX, int startY) {
        for (int i = 0; i < pattern.getSizeX(); i++) {
            for (int j = 0; j < pattern.getSizeY(); j++) {
                // Handling Overflow
                int x = (startX + i) % sizeX;
                int y = (startY + j) % sizeY;
                simGrid[x][y] = pattern.getCell(i, j);
            }
        }
    }

    public void update() {
        boolean[][] updated = new boolean[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            // Deep Clone
            updated[i] = simGrid[i].clone();
        }

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                // Game of Life Rules
                int neighbors = countNeighbors(x, y);
                if (getCell(x, y)) {
                    if (neighbors < 2) {
                        // Underpopulation
                        updated[x][y] = false;
                    } else updated[x][y] = neighbors == 2 || neighbors == 3; // Living on and Overpopulation
                } else {
                    // Reproduction
                    updated[x][y] = neighbors == 3;
                }
            }
        }
        simGrid = updated;
    }

    public int countNeighbors(int x, int y) {
        int count = 0;
        // Check Left and Right and avoids counting itself
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    // Handling Overflow
                    int nx = (x + dx + sizeX) % sizeX;
                    int ny = (y + dy + sizeY) % sizeY;
                    if (simGrid[nx][ny]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
