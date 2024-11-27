public class PatternGlider extends Pattern {
    private final int sizeX = 3;
    private final int sizeY = 3;
    private final boolean[][] gliderArray;

    public PatternGlider() {
        gliderArray = new boolean[][]{
                {false, true, false},
                {false, false, true},
                {true, true, true}
        };
    }

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

    @Override
    public boolean getCell(int x, int y) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            return gliderArray[x][y];
        }
        return false;
    }
}
