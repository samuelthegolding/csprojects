package Pieces;

public class King extends Piece{
    public King(Color color) { super(color); }
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        return false;
    }
}
