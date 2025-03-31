package com.guy7cc.voxelodyssey.game.system;

public enum VOElement {
    BASE(0xFFFFFF),
    FLAME(0xFF5555),
    FROST(0x55FFFF),
    LIGHTNING(0xFFFF55),
    VENOM(0x55FF55),
    WITHER(0xAAAAAA),
    PHANTOM(0x9370DB),
    DIVINE(0xFFAA00),
    ASTRAL(0x7FFFD4);

    public final int color;

    VOElement(int color) {
        this.color = color;
    }
}
