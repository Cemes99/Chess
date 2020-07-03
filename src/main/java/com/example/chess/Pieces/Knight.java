package com.example.chess.Pieces;

import com.example.chess.Coordinates;
import com.example.chess.Position;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Coordinates> allowToMoves(Coordinates coordinates, Position[][] board) {
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();
        allowedMoves.clear();

        int y[] = {coordinates.getY() - 2, coordinates.getY() - 1, coordinates.getY() + 1, coordinates.getY() + 2};
        int x[] = {coordinates.getX() - 1, coordinates.getX() + 1, coordinates.getX() - 2, coordinates.getX() + 2};

        int x1, x2;
        for(int i = 0; i < 4; i++) {
            if(i == 0 || i == 3) {
                x1 = x[0];
                x2 = x[1];
            } else {
                x1 = x[2];
                x2 = x[3];
            }

            if(y[i] >= 0 && y[i] < 8 && x1 >= 0 && x1 < 8) {
                if(board[y[i]][x1].getPiece() == null) allowedMoves.add(new Coordinates(y[i], x1));
                else {
                    if(board[y[i]][x1].getPiece().isWhite() != isWhite())
                        allowedMoves.add(new Coordinates(y[i], x1));
                }
            }

            if(y[i] >= 0 && y[i] < 8 && x2 >= 0 && x2 < 8) {
                if(board[y[i]][x2].getPiece() == null) allowedMoves.add(new Coordinates(y[i], x2));
                else {
                    if(board[y[i]][x2].getPiece().isWhite() != isWhite())
                        allowedMoves.add(new Coordinates(y[i], x2));
                }
            }
        }

        return allowedMoves;
    }
}
