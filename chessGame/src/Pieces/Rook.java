package Pieces;

public class Rook extends Piece {
    public Rook (Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        return (startX != endX) ^ (startY != endY);
    }
}
