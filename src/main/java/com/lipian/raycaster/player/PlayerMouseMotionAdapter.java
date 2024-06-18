package com.lipian.raycaster.player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PlayerMouseMotionAdapter extends MouseMotionAdapter {
    private final Player player;
    private int factor;
    private final int width;

    public PlayerMouseMotionAdapter(Player player) {
        this.player = player;
        width = (int) Toolkit.getDefaultToolkit()
                .getScreenSize()
                .getWidth();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() == width) {
            moveMouse(1, e.getYOnScreen());
            factor++;
        } else if (e.getX() == 0) {
            moveMouse(width - 1, e.getYOnScreen());
            factor--;
        }
        double angle = Math.PI * (e.getX() + factor * width) / 720;
        player.setAngle(angle);
    }

    private void moveMouse(int x, int y) {
        try {
            new Robot().mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
