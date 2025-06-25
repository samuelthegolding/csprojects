package Pieces;

public class Knight extends Piece{
    public Knight(Color color) { super(color); }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        return false;
    }
}
