package com.lipian.raycaster.player;

import lombok.AllArgsConstructor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@AllArgsConstructor
public class PlayerKeyAdapter extends KeyAdapter {
    private Player player;
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.move(Direction.UP);
            case KeyEvent.VK_D -> player.move(Direction.RIGHT);
            case KeyEvent.VK_S -> player.move(Direction.DOWN);
            case KeyEvent.VK_A -> player.move(Direction.LEFT);
        }
    }
}
