package com.lipian.raycaster.player;

import com.lipian.raycaster.map.Map;

import java.awt.*;

public class DigitalDifferentialAnalyzer {
    public static void drawLine(Graphics g, int x1, int y1, double length, double angle) {
        int x2 = (int) (x1 + length * Math.cos(angle));
        int y2 = (int) (y1 + length * Math.sin(angle));

        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        double xIncrement = (double) dx / steps;
        double yIncrement = (double) dy / steps;

        double x = x1;
        double y = y1;

        for (int i = 0; i <= steps; i++) {
            g.drawRect((int) Math.round(x), (int) Math.round(y), 0, 0);
            x += xIncrement;
            y += yIncrement;
            if (Map.hitWall((int) x, (int) y)) return;
        }
    }

}
