package Pieces;

public class Bishop extends Piece{
    public Bishop(Color color){
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        return endY-startY == endX-startX;
    }


}
