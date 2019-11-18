package com.sergey.zhuravlev.shape;

import java.awt.*;

public abstract class Shape {

    public abstract Point[] getPoint();

    public abstract int[] getPointX();

    public abstract int[] getPointY();

    public abstract int getPointSize();

    public void draw(Graphics g) {
        Color saveColor = g.getColor();
        g.setColor(Color.RED);
        g.drawPolygon(this.getPointX(), this.getPointY(), this.getPointSize());
        g.setColor(saveColor);
    }

}
