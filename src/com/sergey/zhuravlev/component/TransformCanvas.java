package com.sergey.zhuravlev.component;

import com.sergey.zhuravlev.shape.Shape;
import com.sergey.zhuravlev.transform.Transform;

import javax.swing.*;
import java.awt.*;

public class TransformCanvas extends JPanel implements Runnable {

    private Shape startShape;
    private Shape endShape;

    private final Thread renderThread;

    private Transform transform;

    public TransformCanvas(int width, int height, int framePerSecond) {
        this.setBackground(Color.BLACK);
        this.setSize(width, height);

        renderThread = new Thread(this);
    }

    @Override
    public void paint(Graphics g) {
        clearStage(g);
        if (endShape != null && startShape != null && transform != null) {
            drawInfo(g);
            endShape.draw(g);
            transform.draw(g);
        }
    }

    private void clearStage(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawInfo(Graphics g) {
        Color saveColor = g.getColor();
        g.setColor(Color.GREEN);
        if (transform != null && startShape != null && endShape != null) {
            g.drawString(String.format("Фрейм %d/%d, преобразование %d-угольника в %d-угольник",
                    transform.getCurrentFrame(), transform.getFrameCount(),
                    startShape.getPointSize(),
                    endShape.getPointSize()),
                    15, 50);
        }
        g.setColor(saveColor);
    }

    @Override
    public void run() {
        while (true) {
            try {
                transform.render();
                repaint();
                Thread.sleep(16);
            } catch (InterruptedException ignored) {
                break;
            }
        }
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void setStartShape(Shape startShape) {
        this.startShape = startShape;
    }

    public void setEndShape(Shape endShape) {
        this.endShape = endShape;
    }

    public void startTransform() {
        transform.init(startShape, endShape);
        renderThread.start();
    }
}
