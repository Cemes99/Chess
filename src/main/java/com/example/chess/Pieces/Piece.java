package com.example.chess.Pieces;

import com.example.chess.Coordinates;
import com.example.chess.Position;

import java.util.ArrayList;

public abstract class Piece {
    private boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    public ArrayList<Coordinates> allowToMoves(Coordinates coordinates , Position[][] board){
        ArrayList<Coordinates> allowedMoves = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) allowedMoves.add(new Coordinates(i, j));
        }

        return allowedMoves;
    }

    public boolean isWhite() {
        return white;
    }
}
