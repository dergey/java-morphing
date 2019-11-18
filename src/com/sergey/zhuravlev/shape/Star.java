package com.sergey.zhuravlev.shape;

import java.awt.*;

public class Star extends Shape {

    private Point[] points;
    private int[] pointX;
    private int[] pointY;

    public Star(int sides, int radius, int offsetX, int offsetY) {
        points = new Point[sides];
        pointX = new int[sides];
        pointY = new int[sides];
        double theta = 2 * Math.PI / sides;
        for (int i = 0; i < sides * 2; i += 2) {
            int x = (int) Math.round(radius * Math.cos(theta * i));
            int y = (int) Math.round(radius * Math.sin(theta * i));
            pointX[i / 2] = x + offsetX;
            pointY[i / 2] = y + offsetY;
            points[i / 2] = new Point(x + offsetX, y + offsetY);
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
