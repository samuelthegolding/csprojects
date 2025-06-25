import Pieces.*;

import java.util.Collections;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        this.initBoard();
    }

    private void initBoard() {
        // Back Rank(White)
        board[0][0] = new Rook(Color.WHITE);
        board[0][1] = new Knight(Color.WHITE);
        board[0][2] = new Bishop(Color.WHITE);
        board[0][3] = new Queen(Color.WHITE);
        board[0][4] = new King(Color.WHITE);
        board[0][5] = new Bishop(Color.WHITE);
        board[0][6] = new Knight(Color.WHITE);

        // Pawns(White)
        board[0][7] = new Rook(Color.WHITE);
        board[1][0] = new Pawn(Color.WHITE);
        board[1][1] = new Pawn(Color.WHITE);
        board[1][2] = new Pawn(Color.WHITE);
        board[1][3] = new Pawn(Color.WHITE);
        board[1][4] = new Pawn(Color.WHITE);
        board[1][5] = new Pawn(Color.WHITE);
        board[1][6] = new Pawn(Color.WHITE);
        board[1][7] = new Pawn(Color.WHITE);

        // Back Rank(Black)
        board[7][0] = new Rook(Color.BLACK);
        board[7][1] = new Knight(Color.BLACK);
        board[7][2] = new Bishop(Color.BLACK);
        board[7][3] = new Queen(Color.BLACK);
        board[7][4] = new King(Color.BLACK);
        board[7][5] = new Bishop(Color.BLACK);
        board[7][6] = new Knight(Color.BLACK);
        board[7][7] = new Rook(Color.BLACK);

        // Pawns(Black
        board[6][0] = new Pawn(Color.BLACK);
        board[6][1] = new Pawn(Color.BLACK);
        board[6][2] = new Pawn(Color.BLACK);
        board[6][3] = new Pawn(Color.BLACK);
        board[6][4] = new Pawn(Color.BLACK);
        board[6][5] = new Pawn(Color.BLACK);
        board[6][6] = new Pawn(Color.BLACK);
        board[6][7] = new Pawn(Color.BLACK);
    }
}
