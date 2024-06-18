package com.lipian.raycaster.map;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public enum Wall {
    EMPTY(null),
    TEST("test.png");

    @Getter
    private Image image;

    Wall(String fileName) {
        if (fileName == null) {
            image = null;
            return;
        }
        String path = String.format("src/main/resources/images/%s", fileName);
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image getImage(int wallType) {
        Wall wall;
        switch (wallType) {
            case 1 -> wall = Wall.TEST;
            default -> wall = Wall.EMPTY;
        }
        return wall.getImage();
    }
}

