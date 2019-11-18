package com.sergey.zhuravlev.shape;

import java.awt.*;

public class RegularPolygon extends Shape {

    private Point[] points;
    private int[] pointX;
    private int[] pointY;

    public RegularPolygon(int sides, int radius, int offsetX, int offsetY, double angel) {
        pointX = new int[sides];
        pointY = new int[sides];
        points = new Point[sides];
        double theta = 2 * Math.PI / sides;
        for (int i = 0; i < sides; ++i) {
            int x = (int) Math.round(radius * Math.cos(theta * i + angel));
            int y = (int) Math.round(radius * Math.sin(theta * i + angel));
            pointX[i] = x + offsetX;
            pointY[i] = y + offsetY;
            points[i] = new Point(pointX[i], pointY[i]);
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
