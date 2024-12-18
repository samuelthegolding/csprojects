public class PatternBlock extends Pattern {
    private final int sizeX = 2;
    private final int sizeY = 2;
    private final boolean[][] blockArray;

    public PatternBlock() {
        blockArray = new boolean[][]{
                {true, true},
                {true, true}};
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
            return blockArray[x][y];
        }
        return false;
    }
}
