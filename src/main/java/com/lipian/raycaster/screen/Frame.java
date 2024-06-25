package com.lipian.raycaster.screen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("Raycaster");
        ImageIcon icon = new ImageIcon("src/main/resources/images/icon.png");
        setIconImage(icon.getImage());
        pack();
        setResizable(false);
        addEscapeKeyListener();
    }

    private void addEscapeKeyListener() {
        String escapeKey = "ESCAPE";
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(escapeKey);
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();
        inputMap.put(escapeKeyStroke, escapeKey);
        actionMap.put(escapeKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
