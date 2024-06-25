package com.lipian.raycaster.player;

import com.lipian.raycaster.map.Map;
import lombok.Getter;
import lombok.Setter;

public class Player {
    public final int SIZE = 30, SPEED = 3;
    @Getter
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
        int x = this.x + dx;
        int y = this.y + dy;
        int distance = DigitalDifferentialAnalyzer.calculateDistance(x, y, angle);
        return distance >= SIZE / 2;
    }
}
