package com.example.chess.ListLaw;

import com.example.chess.Pieces.Piece;

public class Law {
    private Piece piece;

    public Law(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
