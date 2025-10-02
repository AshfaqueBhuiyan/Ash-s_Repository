package lab6;

import java.awt.Point;
import java.util.ArrayList;

public class Polyline {
    private ArrayList<Point> points;
    private String color;
    private int lineWidth;

    public Polyline(String color, int lineWidth) {
        this.color = color;
        this.lineWidth = lineWidth;
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    // Getters
    public String getColor() {
        return color;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}

