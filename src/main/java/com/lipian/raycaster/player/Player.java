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
        int dx = (int) (SPEED * Math.cos(angle + phase));
        int dy = (int) (SPEED * Math.sin(angle + phase));
        if (canMove(dx, dy)) {
            x += dx;
            y += dy;
        }
    }

    private boolean canMove(int dx, int dy) {
        int tempX = x + dx;
        int tempY = y + dy;
        boolean condition = true;
        for (int i = -SIZE/2; i <= SIZE/2; i += SIZE)
            condition = condition
                    && !Map.hitWall(tempX + i , tempY + i)
                    && !Map.hitWall(tempX + i , tempY - i);
        return condition;
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
