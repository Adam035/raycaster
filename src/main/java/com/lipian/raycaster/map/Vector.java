package com.lipian.raycaster.map;

import java.awt.*;

public record Vector(double x, double y, double z) {

    public Vector dot(double scalar) {
        return new Vector(scalar * x,scalar * y, scalar * z);
    }

    public double dot(Vector other) {
        return x * other.x() + y * other.y() + z * other.z();
    }

    public Vector invert() {
        return dot(-1);
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x(), y + other.y(), z + other.z());
    }

    public Vector minus(Vector other) {
        return add(other.invert());
    }

    public double norm() {
        return (double) Math.sqrt(dot(this));
    }

    public void paint(int x, int y, Graphics g) {
        g.drawLine(x, y, (int) this.x + x, (int) this.y + y);
    }
}
