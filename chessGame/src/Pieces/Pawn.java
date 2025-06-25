package Pieces;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        if (this.color == Color.WHITE) {
            if (startY == 2) return startX == endX && (endY - startY == 1||endY - startY == 2);
            return startX == endX && endY - startY == 1;
        }
        return false;
    }
}
