package com.sergey.zhuravlev.transform;

import com.sergey.zhuravlev.shape.Shape;

import java.awt.*;

public interface Transform {

    void init(Shape startShape, Shape endShape);

    void render();

    void draw(Graphics g);

    Integer getFrameCount();

    Integer getCurrentFrame();

}
