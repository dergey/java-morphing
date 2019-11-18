package com.sergey.zhuravlev.shape;

import java.awt.*;

public class CustomPolygon extends Shape {

    private Point[] points;
    private int[] pointX;
    private int[] pointY;

    public CustomPolygon(Point[] points) {
        this.pointX = new int[points.length];
        this.pointY = new int[points.length];
        this.points = points;
        for (int i = 0; i < points.length; i++) {
            pointX[i] = points[i].x;
            pointY[i] = points[i].y;
        }
    }

    @Override
    public Point[] getPoint() {
        return points;
    }

    @Override
    public int[] getPointX() {
        return pointX;
    }

    @Override
    public int[] getPointY() {
        return pointY;
    }

    @Override
    public int getPointSize() {
        return points.length;
    }
}
