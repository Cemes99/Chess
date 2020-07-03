package com.example.chess.Pieces;

import com.example.chess.Coordinates;
import com.example.chess.Position;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Coordinates> allowToMoves(Coordinates coordinates, Position[][] board) {
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();
        allowedMoves.clear();

        int i = coordinates.getY() - 1, j = coordinates.getX() - 1;
        // top_left
        while(i >= 0 && j >= 0) {
            if(board[i][j].getPiece() == null)
                allowedMoves.add(new Coordinates(i, j));
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
            if(board[i][j].getPiece() == null)
                allowedMoves.add(new Coordinates(i, j));
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
            if(board[i][j].getPiece() == null)
                allowedMoves.add(new Coordinates(i, j));
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
            if(board[i][j].getPiece() == null)
                allowedMoves.add(new Coordinates(i, j));
            else {
                if(board[i][j].getPiece().isWhite() != isWhite())
                    allowedMoves.add(new Coordinates(i, j));

                // stop check
                break;
            }
            i++;
            j++;
        }

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
}
