package com.lipian.raycaster.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {
    public final static int SIZE = 16;
    private static Map instance;
    private final int[][] walls;

    private Map() {
        walls = loadMap("src/main/resources/map.txt");
    }

    public static Map getInstance() {
        if (instance == null)
            instance = new Map();
        return instance;
    }

    public static int[][] loadMap(String filePath) {
        int[] dimensions = getMapDimensions(filePath);
        int rows = dimensions[0];
        int cols = dimensions[1];
        return readMapData(filePath, rows, cols);
    }

    private static int[] getMapDimensions(String filePath) {
        int rows = 0;
        int cols = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows++;
                String[] values = line.split(",\\s*");
                if (values.length > cols) cols = values.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[]{rows, cols};
    }

    private static int[][] readMapData(String filePath, int rows, int cols) {
        int[][] array = new int[rows][cols];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentRow = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",\\s*");
                for (int i = 0; i < values.length; i++)
                    array[currentRow][i] = Integer.parseInt(values[i]);
                currentRow++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public boolean hitWall(int x, int y) {
        int mapX = x / SIZE;
        int mapY = y / SIZE;
        return walls[mapY][mapX] != 0;
    }
}
