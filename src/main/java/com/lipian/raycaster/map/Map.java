package com.lipian.raycaster.map;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
    public final static int SIZE = 32, ROWS = 22, COLS = 40;
    private static final int[][] walls = loadMap();

    private static int[][] loadMap() {
        int[][] array = new int[ROWS][COLS];
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/map.txt"))) {
            String line;
            int currentRow = 0;
            while ((line = br.readLine()) != null && currentRow < ROWS) {
                String[] values = line.split(",\\s*");
                for (int i = 0; i < values.length && i < COLS; i++)
                    array[currentRow][i] = Integer.parseInt(values[i]);
                currentRow++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }


    public static boolean hitWall(int x, int y) {
        int mapX = x / SIZE;
        int mapY = y / SIZE;
        return walls[mapY][mapX] != 0;
    }

    public void paint(Graphics g) {
        for (int x = 0; x < 1280; x += SIZE)
            for (int y = 0; y < 720; y += SIZE) {
                g.drawLine(x, 0, x, 720);
                g.drawLine(0, y, 1280, y);
            }

        for (int y = 0; y < walls.length; y++)
            for (int x = 0; x < walls[0].length; x++)
                paintWall(x, y, g);
    }

    private void paintWall(int x, int y, Graphics g) {
        Image image = Wall.getImage(walls[y][x]);
        if (image != null)
            g.drawImage(image, x * SIZE, y * SIZE, null);
    }



}
