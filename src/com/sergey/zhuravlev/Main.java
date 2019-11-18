package com.sergey.zhuravlev;

import com.sergey.zhuravlev.component.TransformCanvas;
import com.sergey.zhuravlev.shape.RegularPolygon;
import com.sergey.zhuravlev.transform.RandomShapeTransform;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        Frame f = new Frame("Лабораторная работа 1");
        TransformCanvas canvas = new TransformCanvas(640, 480, 60);
        f.add(canvas);
        f.setLayout(null);
        f.setSize(640, 480);
        f.setVisible(true);
        canvas.setStartShape(new RegularPolygon(6, 50, 120, 240, Math.toRadians(90)));
        canvas.setEndShape(new RegularPolygon(3, 50, 520, 240, Math.toRadians(0)));
        canvas.setTransform(new RandomShapeTransform(120, false));
        canvas.startTransform();
    }
}
