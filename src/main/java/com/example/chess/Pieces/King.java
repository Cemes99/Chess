package com.example.chess.Pieces;

import com.example.chess.Coordinates;
import com.example.chess.Position;

import java.util.ArrayList;

public class King extends Piece{
    private boolean moved;

    public King(boolean white) {
        super(white);
        moved = false;
    }

    @Override
    public ArrayList<Coordinates> allowToMoves(Coordinates coordinates, Position[][] board) {
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();
        allowedMoves.clear();

        // around
        for(int i = coordinates.getY() - 1; i <= coordinates.getY() + 1; i++) {
            for(int j = coordinates.getX() - 1; j <= coordinates.getX() + 1; j++) {
                if ( i >= 0 && i < 8 && j >= 0 && j < 8 &&
                        (i != coordinates.getY() || j != coordinates.getX()) ) {

                    if (board[i][j].getPiece() == null) allowedMoves.add(new Coordinates(i, j));
                    else if (board[i][j].getPiece().isWhite() != isWhite())
                        allowedMoves.add(new Coordinates(i, j));
                }
            }
        }

        // artificial castling
        if(!moved) {
            int i;
            for(i = coordinates.getX() - 1; i > 0; i--) {
                if(board[coordinates.getY()][i].getPiece() != null) break;
            }

            if(i == 0 && board[coordinates.getY()][i].getPiece() instanceof Rook &&
                    board[coordinates.getY()][i].getPiece().isWhite() == isWhite() &&
                    !((Rook) board[coordinates.getY()][i].getPiece()).isMoved()) {
                allowedMoves.add(new Coordinates(coordinates.getY(), coordinates.getX() - 2));
            }

            for(i = coordinates.getX() + 1; i < 7; i++) {
                if(board[coordinates.getY()][i].getPiece() != null) break;
            }

            if(i == 7 && board[coordinates.getY()][i].getPiece() instanceof Rook &&
                    board[coordinates.getY()][i].getPiece().isWhite() == isWhite() &&
                    !((Rook) board[coordinates.getY()][i].getPiece()).isMoved()) {
                allowedMoves.add(new Coordinates(coordinates.getY(), coordinates.getX() + 2));
            }
        }

        return allowedMoves;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
