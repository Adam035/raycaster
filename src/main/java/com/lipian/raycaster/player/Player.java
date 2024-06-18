package com.lipian.raycaster.player;

import com.lipian.raycaster.map.Map;
import com.lipian.raycaster.map.Vector;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Player {
    private final int SIZE = 20, SPEED = 5, RANGE = 500;
    @Setter
    private Vector pos;
    @Getter
    @Setter
    private double angle;

    public Player() {
        pos = new Vector(2 * Map.SIZE, 2 * Map.SIZE, 0);
    }

    protected void move(Direction direction) {
        double phase = direction.getPhase();
        double x = SPEED * Math.cos(angle + phase);
        double y = SPEED * Math.sin(angle + phase);
        pos = pos.add(new Vector(x, y, 0));
    }

    public void paint(Graphics g) {
        g.fillOval((int) pos.x() - SIZE / 2, (int) pos.y() - SIZE / 2, SIZE, SIZE);
        drawLines(g);
    }

    private void drawLines(Graphics g) {
        int x = (int) pos.x();
        int y = (int) pos.y();
        double maxAngle = Math.PI / 4;
        for (double angle = -maxAngle; angle < maxAngle; angle += 0.01)
            DigitalDifferentialAnalyzer.drawLine(g, x, y, RANGE, this.angle + angle);
    }
}
