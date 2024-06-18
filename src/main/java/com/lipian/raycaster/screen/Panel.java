package com.lipian.raycaster.screen;

import com.lipian.raycaster.map.Map;
import com.lipian.raycaster.player.Player;
import com.lipian.raycaster.player.PlayerKeyAdapter;
import com.lipian.raycaster.player.PlayerMouseMotionAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    private static final int DELAY = 0;
    Player player;
    Map map;

    public Panel() {
        map = new Map();
        player = new Player();
        addKeyListener(new PlayerKeyAdapter(player));
        addMouseMotionListener(new PlayerMouseMotionAdapter(player));
        hideCursor();
        setFocusable(true);
        start();
    }

    private void hideCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("");
        Point hotspot = new Point(0, 0);
        Cursor invisibleCursor = toolkit
                .createCustomCursor(image, hotspot, "InvisibleCursor");
        setCursor(invisibleCursor);
    }

    private void start() {
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        map.paint(g);
        player.paint(g);
        g.setColor(Color.green);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
