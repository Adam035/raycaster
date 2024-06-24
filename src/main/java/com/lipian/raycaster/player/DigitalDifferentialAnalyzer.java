package com.lipian.raycaster.player;

import com.lipian.raycaster.map.Map;

public class DigitalDifferentialAnalyzer {
    public static int calculateDistance(double x, double y, double angle) {
        double deltaDistX = Math.abs(1 / Math.cos(angle));
        double deltaDistY = Math.abs(1 / Math.sin(angle));
        int stepX = (Math.cos(angle) < 0) ? -1 : 1;
        int stepY = (Math.sin(angle) < 0) ? -1 : 1;

        double sideDistX = (stepX == -1) ? (x - Math.floor(x)) * deltaDistX : (Math.ceil(x) - x) * deltaDistX;
        double sideDistY = (stepY == -1) ? (y - Math.floor(y)) * deltaDistY : (Math.ceil(y) - y) * deltaDistY;
        int mapX = (int) x;
        int mapY = (int) y;

        boolean hit = false;
        int side = 0;
        while (!hit) {
            if (sideDistX < sideDistY) {
                sideDistX += deltaDistX;
                mapX += stepX;
                side = 0;
            } else {
                sideDistY += deltaDistY;
                mapY += stepY;
                side = 1;
            }
            if (Map.getInstance().hitWall(mapX, mapY)) hit = true;
        }
        if (side == 0)
            return (int) ((mapX - x + (1 - stepX) / 2) / Math.cos(angle));
        else
            return (int) ((mapY - y + (1 - stepY) / 2) / Math.sin(angle));
    }

    public static int calculateDistance(Player player, double angle) {
        return calculateDistance(player.getX(), player.getY(), angle);
    }
}
