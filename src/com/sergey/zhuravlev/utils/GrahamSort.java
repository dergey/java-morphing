package com.sergey.zhuravlev.utils;

import java.awt.Point;
import java.util.*;

public class GrahamSort {

    private static Point getLowestPoint(List<Point> points) {
        Point lowest = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point temp = points.get(i);
            if (temp.y < lowest.y || (temp.y == lowest.y && temp.x < lowest.x)) {
                lowest = temp;
            }
        }
        return lowest;
    }

    public static List<Point> sort(List<Point> points) {
        final Point lowest = getLowestPoint(points);

        Comparator<Point> pointComparator = new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                if (a == b || a.equals(b)) {
                    return 0;
                }

                double thetaA = Math.atan2((long) a.y - lowest.y, (long) a.x - lowest.x);
                double thetaB = Math.atan2((long) b.y - lowest.y, (long) b.x - lowest.x);

                if (thetaA < thetaB) {
                    return -1;
                } else if (thetaA > thetaB) {
                    return 1;
                } else {
                    double distanceA = Math.sqrt((((long) lowest.x - a.x) * ((long) lowest.x - a.x)) +
                            (((long) lowest.y - a.y) * ((long) lowest.y - a.y)));
                    double distanceB = Math.sqrt((((long) lowest.x - b.x) * ((long) lowest.x - b.x)) +
                            (((long) lowest.y - b.y) * ((long) lowest.y - b.y)));

                    if (distanceA < distanceB) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        };

        points.sort(pointComparator);
        return points;
    }

}