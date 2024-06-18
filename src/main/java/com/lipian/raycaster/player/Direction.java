package com.lipian.raycaster.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {
    RIGHT(Math.PI / 2),
    DOWN(Math.PI),
    LEFT(3 * Math.PI / 2),
    UP(0);

    private final double phase;
}
