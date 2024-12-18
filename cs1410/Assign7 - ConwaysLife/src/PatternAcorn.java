public class PatternAcorn extends Pattern {
    private final int sizeX = 7;
    private final int sizeY = 3;
    private final boolean[][] acornArray;

    public PatternAcorn() {
        acornArray = new boolean[][]{
                {false, true, false, false, false, false, false},
                {false, false, false, true, false, false, false},
                {true, true, false, false, true, true, true}
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
            return acornArray[y][x];
        }
        return false;
    }
}
