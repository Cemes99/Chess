package com.example.chess;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(){

    }

    public Coordinates(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
