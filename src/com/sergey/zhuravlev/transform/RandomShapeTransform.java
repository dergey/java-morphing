package com.sergey.zhuravlev.transform;

import com.sergey.zhuravlev.shape.CustomPolygon;
import com.sergey.zhuravlev.shape.Line;
import com.sergey.zhuravlev.shape.Shape;
import com.sergey.zhuravlev.utils.GrahamSort;
import com.sergey.zhuravlev.utils.MemorizedRandom;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RandomShapeTransform implements Transform {

    private final int frameCount;
    private final MemorizedRandom random;

    private final List<Line> vectors;
    private final List<Point.Double> offsets;

    private int currentFrame;
    private int maxShapePoint;
    private List<Point> startPoints;
    private Shape subShape;

    private final boolean debug;

    public RandomShapeTransform(int frameCount, boolean debug) {
        this.frameCount = frameCount;
        this.maxShapePoint = 0;
        this.currentFrame = 0;
        this.debug = debug;
        this.random = new MemorizedRandom();
        this.vectors = new ArrayList<>();
        this.offsets = new ArrayList<>();
    }

    @Override
    public void init(Shape startShape, Shape endShape) {
        maxShapePoint = Math.max(startShape.getPointSize(), endShape.getPointSize());

        startPoints = new ArrayList<>();
        List<Point> endPoints = new ArrayList<>();

        for (int i = 0; i < maxShapePoint; i++) {
            Point startPoint = startShape.getPoint()[random.nextInt(startShape, startShape.getPointSize())];
            startPoints.add(startPoint);
            Point endPoint = endShape.getPoint()[random.nextInt(endShape, endShape.getPointSize())];
            endPoints.add(endPoint);
        }

        startPoints = GrahamSort.sort(startPoints);
        endPoints = GrahamSort.sort(endPoints);

        for (int i = 0; i < maxShapePoint; i++) {
            vectors.add(new Line(startPoints.get(i), endPoints.get(i)));
        }

        for (Line vector : vectors) {
            offsets.add(calculateVectorOffset(vector));
        }

    }

    @Override
    public void render() {
        Point[] points = new Point[maxShapePoint];

        for (int i = 0; i < offsets.size(); i++) {
            Point startPoint = startPoints.get(i);
            Point.Double offset = offsets.get(i);
            points[i] = new Point((int) (startPoint.getX() + Math.round(offset.x * currentFrame)),
                    (int) (startPoint.getY() + Math.round(offset.y * currentFrame)));
        }
        subShape = new CustomPolygon(points);
        currentFrame++;
        if (currentFrame >= frameCount) {
            currentFrame = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        subShape.draw(g);
        if (debug) {
            vectors.forEach(v -> drawLine(g, v));
        }
    }

    private void drawLine(Graphics g, Line vector) {
        Color saveColor = g.getColor();
        g.setColor(Color.GREEN);
        g.drawLine(vector.getX(), vector.getY(), vector.getEndX(), vector.getEndY());
        g.setColor(saveColor);
    }

    @Override
    public Integer getFrameCount() {
        return frameCount;
    }

    @Override
    public Integer getCurrentFrame() {
        return currentFrame;
    }

    private Point.Double calculateVectorOffset(Line vector) {
        return calculateVectorOffset(vector.getX(), vector.getY(), vector.getEndX(), vector.getEndY());
    }

    private Point.Double calculateVectorOffset(int x, int y, int endX, int endY) {
        int height = endY - y;
        int width = endX - x;
        if (height == 0 && width == 0) {
            return new Point.Double(0, 0);
        } else if (height == 0) {
            double offsetX = width * 1.0 / frameCount;
            return new Point.Double(offsetX, 0);
        } else if (width == 0) {
            double offsetY = height * 1.0 / frameCount;
            return new Point.Double(0, offsetY);
        } else {
            double angle = Math.atan2(height, width);
            double hypotenuse = height * 1.0 / Math.sin(angle);
            double offsetX = Math.cos(angle) * hypotenuse / frameCount;
            double offsetY = Math.sin(angle) * hypotenuse / frameCount;
            return new Point.Double(offsetX, offsetY);
        }
    }

}
