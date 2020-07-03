package com.example.chess.Pieces;

import com.example.chess.Coordinates;
import com.example.chess.Position;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Coordinates> allowToMoves(Coordinates coordinates, Position[][] board) {
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();
        allowedMoves.clear();
        Coordinates c;

        int i = coordinates.getY() - 1, j = coordinates.getX() - 1;
        // top_left
        while(i >= 0 && j >= 0) {
            if(board[i][j].getPiece() == null) allowedMoves.add(new Coordinates(i, j));
            else {
                if(board[i][j].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(i, j));

                // stop check
                break;
            }
            i--;
            j--;
        }

        // top_right
        i = coordinates.getY() - 1;
        j = coordinates.getX() + 1;
        while(i >= 0 && j < 8) {
            if(board[i][j].getPiece() == null) allowedMoves.add(new Coordinates(i, j));
            else {
                if(board[i][j].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(i, j));

                // stop check
                break;
            }

            i--;
            j++;
        }

        // under_left
        i = coordinates.getY() + 1;
        j = coordinates.getX() - 1;
        while(i < 8 && j >= 0) {
            if(board[i][j].getPiece() == null) allowedMoves.add(new Coordinates(i, j));
            else {
                if(board[i][j].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(i, j));

                // stop check
                break;
            }
            i++;
            j--;
        }

        // under_right
        i = coordinates.getY() + 1;
        j = coordinates.getX() + 1;
        while(i < 8 && j < 8) {
            if(board[i][j].getPiece() == null) allowedMoves.add(new Coordinates(i, j));
            else {
                if(board[i][j].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(i, j));

                // stop check
                break;
            }
            i++;
            j++;
        }

        return allowedMoves;
    }
}
