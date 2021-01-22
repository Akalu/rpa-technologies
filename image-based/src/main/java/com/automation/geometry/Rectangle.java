package com.automation.geometry;

public class Rectangle {
    private final int x;
    private final int y;
    private final int w;
    private final int h;

    public Rectangle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    @Override
    public String toString() {
        return "Rectangle [x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + "]";
    }

}
