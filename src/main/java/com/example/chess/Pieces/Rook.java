package com.example.chess.Pieces;

import com.example.chess.Coordinates;
import com.example.chess.Position;

import java.util.ArrayList;

public class Rook extends Piece {
    private boolean moved;

    public Rook(boolean white) {
        super(white);
        moved = false;
    }

    @Override
    public ArrayList<Coordinates> allowToMoves(Coordinates coordinates, Position[][] board) {
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();
        allowedMoves.clear();

        int i;
        // top
        for(i = coordinates.getY() - 1; i >= 0; i--) {
            if(board[i][coordinates.getX()].getPiece() == null)
                allowedMoves.add(new Coordinates(i, coordinates.getX()));
            else {
                if(board[i][coordinates.getX()].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(i, coordinates.getX()));

                // stop check
                break;
            }
        }

        // under
        for(i = coordinates.getY() + 1; i < 8; i++) {
            if(board[i][coordinates.getX()].getPiece() == null)
                allowedMoves.add(new Coordinates(i, coordinates.getX()));
            else {
                if(board[i][coordinates.getX()].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(i, coordinates.getX()));

                // stop check
                break;
            }
        }

        // left
        for(i = coordinates.getX() - 1; i >= 0; i--) {
            if(board[coordinates.getY()][i].getPiece() == null)
                allowedMoves.add(new Coordinates(coordinates.getY(), i));
            else {
                if(board[coordinates.getY()][i].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(coordinates.getY(), i));

                // stop check
                break;
            }
        }

        // right
        for(i = coordinates.getX() + 1; i < 8; i++) {
            if(board[coordinates.getY()][i].getPiece() == null)
                allowedMoves.add(new Coordinates(coordinates.getY(), i));
            else {
                if(board[coordinates.getY()][i].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(coordinates.getY(), i));

                // stop check
                break;
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
