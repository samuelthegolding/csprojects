public class PatternBlinker extends Pattern {
    private final int sizeX = 3;
    private final int sizeY = 1;
    private final boolean[][] blinkerArray;

    public PatternBlinker() {
        blinkerArray = new boolean[][]{{true}, {true}, {true}};
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
            return blinkerArray[x][y];
        }
        return false;
    }

}
