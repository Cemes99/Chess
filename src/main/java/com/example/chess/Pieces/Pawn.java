package com.example.chess.Pieces;

import android.util.Log;

import com.example.chess.Coordinates;
import com.example.chess.Position;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Coordinates> allowToMoves(Coordinates coordinates, Position[][] board) {
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();
        allowedMoves.clear();

        int x = coordinates.getX(), y = coordinates.getY();

        if(isWhite()) {
            // first move
            if(y == 6 && board[5][x].getPiece() == null && board[4][x].getPiece() == null)
                allowedMoves.add(new Coordinates(4, x));

            if(y > 0 && board[y - 1][x].getPiece() == null)
                allowedMoves.add(new Coordinates(y - 1, x));

            if(x > 0 && y > 0) {
                if(board[y - 1][x - 1].getPiece() != null &&
                        board[y - 1][x - 1].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(y - 1, x - 1));
            }

            if(x < 7 && y > 0) {
                if(board[y - 1][x + 1].getPiece() != null &&
                        board[y - 1][x + 1].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(y - 1, x + 1));
            }
        } else {
            // first move
            if(y == 1 && board[2][x].getPiece() == null && board[3][x].getPiece() == null)
                allowedMoves.add(new Coordinates(3, x));

            if(y < 7 && board[y + 1][x].getPiece() == null)
                allowedMoves.add(new Coordinates(y + 1, x));

            if(x > 0 && y < 7) {
                if(board[y + 1][x - 1].getPiece() != null &&
                        board[y + 1][x - 1].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(y + 1, x - 1));
            }

            if(x < 7 && y < 7) {
                if(board[y + 1][x + 1].getPiece() != null &&
                        board[y + 1][x + 1].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(y + 1, x + 1));
            }
        }
        

        return allowedMoves;
    }
}
