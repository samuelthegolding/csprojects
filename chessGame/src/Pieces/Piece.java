package Pieces;

public abstract class Piece {
    protected Color color;
    protected boolean hasMoved;

    public Piece(Color color) {
        this.color = color;
        hasMoved = false;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY);
}
