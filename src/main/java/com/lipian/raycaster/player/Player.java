package com.lipian.raycaster.player;

import com.lipian.raycaster.map.Map;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Player {
    private final int SIZE = 20, SPEED = 5, RANGE = 500;
    @Setter
    private int x, y;
    @Getter
    @Setter
    private double angle;

    public Player() {
        x = 2 * Map.SIZE;
        y = 2 * Map.SIZE;
    }

    protected void move(Direction direction) {
        double phase = direction.getPhase();
        double x = SPEED * Math.cos(angle + phase);
        double y = SPEED * Math.sin(angle + phase);
        this.x += x;
        this.y += y;
    }

    public void paint(Graphics g) {
        g.fillOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        drawLines(g);
    }

    private void drawLines(Graphics g) {
        double maxAngle = Math.PI / 4;
        for (double angle = -maxAngle; angle < maxAngle; angle += 0.01)
            DigitalDifferentialAnalyzer.drawLine(g, x, y, RANGE, this.angle + angle);
    }
}
