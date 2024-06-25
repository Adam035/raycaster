package com.lipian.raycaster.screen;

import com.lipian.raycaster.map.Map;
import com.lipian.raycaster.player.DigitalDifferentialAnalyzer;
import com.lipian.raycaster.player.Player;
import com.lipian.raycaster.player.PlayerKeyAdapter;
import com.lipian.raycaster.player.PlayerMouseMotionAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    private static final int DELAY = 0;
    private final Toolkit toolkit;
    Player player;
    Map map;

    public Panel() {
        map = Map.getInstance();
        player = new Player();
        addKeyListener(new PlayerKeyAdapter(player));
        addMouseMotionListener(new PlayerMouseMotionAdapter(player));
        toolkit = Toolkit.getDefaultToolkit();
        hideCursor();
        setFocusable(true);
        start();
    }

    private void hideCursor() {
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
        paintWalls(g);
    }

    private void paintWalls(Graphics g) {
        double screenWidth = toolkit.getScreenSize().getWidth();
        double screenHeight = toolkit.getScreenSize().getHeight();
        double fov = Math.toRadians(60);
        for (int x = 0; x < screenWidth; x++) {
            double rayAngle = player.getAngle() - fov / 2 + (x / screenWidth) * fov;
            double deltaAngle = player.getAngle() - rayAngle;
            int distance = DigitalDifferentialAnalyzer.calculateDistance(player, rayAngle);
            distance *= Math.cos(deltaAngle);
            double lineHeight = Map.SIZE * screenHeight / distance;
            int lineStart = (int) ((screenHeight - lineHeight) / 2);
            int lineEnd = (int) ((screenHeight + lineHeight) / 2);
            g.setColor(DigitalDifferentialAnalyzer.getColor());
            g.drawLine(x, lineStart, x, lineEnd);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
