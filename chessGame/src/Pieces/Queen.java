package Pieces;

public class Queen extends Piece {
    public Queen(Color color) { super(color);}

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        return false;
    }
}
