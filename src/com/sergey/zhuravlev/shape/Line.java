package com.sergey.zhuravlev.shape;

import java.awt.*;

public class Line {

    private final int x;
    private final int y;
    private final int endX;
    private final int endY;

    public Line(int x, int y, int endX, int endY) {
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
    }

    public Line(Point startPoint, Point endPoint) {
        x = startPoint.x;
        y = startPoint.y;
        endX = endPoint.x;
        endY = endPoint.y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
